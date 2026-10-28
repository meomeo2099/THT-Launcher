# Boot, automation and playback analysis

Target: reference.apk / org.reference.panel 1.0.2 (16), SHA256 24ab6a3199655531ad80a1d651b961971896fad1715d93799f2bacb4ac93965c.

Static review using REA 6.8.0 / audited JADX 0.7.1. Raw complete JADX source under ../source supports broad review; exact REA method Evidence identified below. No APK execution, device access or mutation.

## Lifecycle and persistence

- BootReceiver.onReceive only handles BOOT_COMPLETED. It calls k8.F0 (automation service start/stop) and k8.E0 (radio/music startup), then starts MainActivity if panel/autostart=true (default true). ActivityNotFoundException/SecurityException are logged. Receiver is non-exported in manifest; reviewed boot entry is not an unprotected public command endpoint.
- k8.X returns true if auto_enabled (default false), stats_enabled (default true), call_pause_music (default true), or Anton popup setting anton_popup (default true). Thus the foreground automation service may remain active even while the user disables vehicle automations. This is explained by other enabled features, not evidence of covert persistence.
- AutomationService.onStartCommand creates a visible low-importance notification on channel automation, starts foreground ID42, instantiates engine automation and call-pause monitors, optionally Anton climate-change overlay worker, and returns START_STICKY (1). If gate false it stops and returns START_NOT_STICKY (2). onBind returns null.
- onDestroy removes pending callbacks, stops location subscriptions, stops stats, unregisters phone listener, disconnects music helper browsers and tears down Anton. Broad source/DEX review finds no quit/quitSafely for the ReferencePanel-automation HandlerThread created by s1 constructor, although q.f quits its separate Anton looper. Repeated service restarts could retain idle worker threads within the app process (low-confidence runtime impact / static lifecycle cleanup gap; not an externally demonstrated DoS).

## Startup media

- k8.E0 defaults music_autoplay=false and radio_autoplay=false. It limits automatic playback to once per boot using Settings.Global.boot_count and panel/autoplay_boot_count plus process flag k8.h. If boot_count unavailable it only allows the early boot window (elapsedRealtime<=300000ms). Radio autoplay invokes RADIO_PLAY; otherwise foreground BootPlaybackService.
- BootPlaybackService.onStartCommand displays notification channel boot_music ID73. It derives preferred package from panel/slot_1 component. It requires selected package and granted MusicNotificationListener access; ordinary entry also requires music_autoplay, while internal PLAY_PREFERRED action bypasses that preference check. Service is non-exported. Return is START_NOT_STICKY.
- hd discovers enabled/exported MediaBrowserServices only in the selected package, connects sequentially with bounded delays, obtains session tokens and uses media controls; active sessions/notifications/fallback media-button broadcasts are restricted to target package. This is music-start assistance, not arbitrary arbitrary-code loading.
- r1.run case1 watchdog checks target active playback and stops when playing or after 25 polls at3sec (initial poll2sec, around74sec). Raw JADX has an inverted/null condition artifact; original r1 DEX at code-unit03b2 checks null before getPlaybackState and confirms proper branch semantics. Do not report a null dereference from this decompiler artifact.

## Engine-triggered vehicle automations

- Global auto_enabled defaults false. Individual flags use panel keys auto_<feature>_on, default false. Poll interval2sec if auto-close sunroof enabled;5sec otherwise. Exceptions are logged and monitoring continues.
- Requires available/ready r4 ECARX bridge. Detects engine/driving property transition and executes heat/vent/airing/climate/apps groups at most once per observed run using s1.c and LinkedHashSet s1.d. Engine-off transition resets flags, clears group set, increments application-launch generation k8.f, stores auto_last_stop, and rearms.
- Optional idle precondition defaults auto_condition=0 (unconditional). Other condition compares elapsed parked minutes to auto_idle_minutes default120. Missing timestamps or backward wall-clock changes yield unknown duration and are accepted by the observed boolean condition. This is a functional edge case; not a verified exploit.
- auto_last_driving written at most every30sec while driving and auto_last_stop on engine stop. These are local panel SharedPreferences timestamps, not evidence of upload.

## Action groups and safeguards

- Speed-based sunroof close: threshold auto_close_kmh default30km/h, rearm below threshold-5. Uses maximum available speed of vehicle sensor and GPS. GPS must have speed, not mock, with elapsed-realtime age0..5000ms. GPS requests only when close enabled and fine-location permission granted; unregisters when disabled. Calling close at threshold is intended explicit automation, not a separately demonstrated malicious action.
- Heat group: optional cold-only threshold default5°C; timed heating default15min; seat level bounded1..supported maximum (configured default2). Optional seat, steering-wheel, rear/front glass/nozzle/defrost controls. Off actions are scheduled after duration when positive.
- Vent group: configured seats only, temperature threshold default25°C, default15min, bounded levels.
- Airing group: default threshold15°C; window ventilation or sunroof opening, optional bounded fan0..8 (default4), default3min close timer. Outside temperature unknown skips this path. This legitimately moves windows/sunroof when enabled; actual vehicle safety interlocks are outside APK evidence.
- Climate group: AUTO command then temperature command after1500ms; temperature setting normalized by z2.h, default22°C, half-degree preference supported.
- Temperature source first uses vehicle external sensor validated[-60,70]°C; otherwise finite cached weather younger than3h. Some temperature-based decisions can therefore use stale weather, a functional limitation.
- s1.b dispatches command and checks resulting state after4sec, retrying up to2 additional times via q1.run only when read-back explicitly false. Null/unknown read-back is not success proof and does not trigger retries. Exceptions are caught/logged. This mitigates unaccepted commands but does not establish physical safety.
- p1.b staggers operations1500ms apart and schedules delayed off actions with Handler uptime-based delays.
- App-start group: enabled list launch delays clamped0..120sec each, cumulative schedule. Guard.offset==0 required. Every app task in u.run and final return-home task checks generation k8.f; engine-stop increment invalidates pending app tasks. Uses explicit component or package launcher Intent with exceptions/fallback, no shell execution.

## Confirmed callback lifecycle gap (conditional functional risk)

Queued thermal/ventilation actions and read-back retries do not use the application-launch generation token. p1.b queues o1 callbacks; o1.run calls the action without rechecking engine state or auto_enabled. q1.run similarly retries after false read-back without engine-generation validation. s1.a clears flags on engine stop or auto-disabled but does not remove these previously queued callbacks. If service remains alive for stats/call/Anton, delayed off or retry actions may outlive the automation cycle that created them, potentially overriding later user/new-cycle settings. onDestroy does cancel callbacks. Static control-flow observation confirmed across p1/s1/q1 and source+DEX o1; physical effect/severity requires compatible device testing. It is not a demonstrated remote exploit. Recommended repair: tag scheduled actions with cycle ID, recheck enabled/run state and cancel relevant callbacks on stop/disable; preserve intentional off cleanup semantics with explicit ownership checks.

## Call-state/music behavior and permission limitation

- Call monitor listens for TelephonyManager call-state32, fetches initial getCallState, and pauses currently playing media every1sec while state RINGING/OFFHOOK; after IDLE waits2500ms and resumes only tracked packages, with fallback media play/pause events if session access unavailable.
- call_pause_music defaults true. This path does not read caller numbers, SMS, contacts or call recordings in reviewed code.
- Manifest lacks READ_PHONE_STATE. listen/getCallState may consequently throw on a given Android build. Both are caught: listener failure logs ReferencePanel.Call / call state unavailable, initial failure becomes null. Service can continue with other features. Thus source proves intended functionality but not actual phone-state access on a device. Vendor/system privileges or platform compatibility can alter availability; no such grant observed.

## Coverage and unknowns

Reviewed BootReceiver, BootPlaybackService, AutomationService plus s1/r1/p1/o1/q1/b1/u/z0/y6/hd/gd/k1/q/k8 relevant paths, originals in dexdump. Broader shared-class unrelated branches not exhaustively audited here. ECARX reflection behavior, firmware policy, vehicle mechanical interlocks, Android grant state, process lifetime and timer effects remain unobserved. Some REA calls failed cleanup ownership inspection; errors retained separately and never treated as successful Evidence. No unchanged failed-call retry used for conclusions.

## Successful REA Evidence

- `automation-start.json`: `ev_dc65fb23e6e7ea9bf6e6eca6440a6cf07a2cea88469a0c32ea67eb49a121d067`
- `b1-a.json`: `ev_b4c1932d6ae96009fde6bab9189e5090b54612484d5db11147e094a4cf77d472`
- `boot-receive.json`: `ev_be4dd9baad78d11a3b96afc5e52e6b6cc2ec5744996a7009b00736f4b9cc965c`
- `k8-E0.json`: `ev_42624b5a916635df6aae74b1b09119ff1387d8f8a80cd8ffd845e330075f6ddb`
- `k8-F0.json`: `ev_d0df9d7b0addd95d6c6515f93eae880f752b87fd2be3361da164c12873ead0b2`
- `k8-X.json`: `ev_b27dd2e2a545fd2222ac05a7f947c75562547700d796ceb0127a69621f52c44f`
- `p1-b.json`: `ev_1e30fd0dc0b3b1c88ee75bc073a9e0d0d3428c3fd8a7321c58ca92f0bfaafe39`
- `playback-start.json`: `ev_a531a5eb939632e76a5714a2c3ffe05b3eb1700f17d1b601c35800d0b20119e1`
- `q1-run.json`: `ev_484c997ace1be7a4ea0a7b9eb988af514eade05095e209f768f0f2b74287689c`
- `r1-run.json`: `ev_d2c8541660a1743200aaa66f5cda498dc46de5ad9064b5b7cca99024b9db4ebc`
- `s1-a.json`: `ev_ab18e9bbd291da4ec22d83505fd0c85ca7324ac7cba1923e8ca64641057ed7ff`
- `s1-f.json`: `ev_1922771f118a329a13c37611f1253951571f7189b0ad32ca97d685270baeac7f`

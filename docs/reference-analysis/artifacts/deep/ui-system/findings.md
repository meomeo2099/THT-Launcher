# MainActivity / system integration deep review

## Scope and evidence

Target SHA-256: `24ab6a3199655531ad80a1d651b961971896fad1715d93799f2bacb4ac93965c`.
REA 6.8.0, JADX headless 0.7.1. APK not executed or modified.

Successful REA observations:

- `main-onActivityResult.json`: `ev_43b15782fcafaa275c94b4b9bd8376745807b98f238aac2a57d04c7732d8f8f2`.
- `k8-Z.json`: `ev_be378042c37d0a45c78f22033d8ee042ecc2ad05df223ed4cd79e34d06fe118d`.
- `jd-onReceive.json`: `ev_db881a38c5caaead7613ebb7ac4a39aa72506804421c777e593d3149a05b61a4`.
- Existing parent surface audit provides MainActivity.onNewIntent and notification/accessibility evidence.

Supporting material (NOT REA Evidence): pinned JADX API export in `../source/`; original DEX disassembly in this directory and parent `dexdump.txt`; `reflective-callsite-inventory.json` inventories reflective call-containing methods. MainActivity class-level REA startup failed process ownership cleanup; `main-class.json` is an error record, not evidence of application behavior. Failed process IDs were checked absent before subsequent different focused method operations. Doctor result retained separately.

## 1. Lifecycle and external intent boundary

Source MainActivity.onCreate (source lines 4340 onward) initializes renderer/media helpers, automation service, vehicle adapter callbacks, stamp verdict logging, optional HVAC popup suppression and onboarding/settings view. It reads only boolean `org.reference.panel.OPEN_HOME` from the starting intent in this observed method. Saved state restores `settings_section` and `settings`.

`onNewIntent` (previous REA evidence) accepts HOME category or OPEN_HOME boolean to bring launcher home and dismiss relevant UI. It does not forward arbitrary inbound intent URI/component/extras to the internal action dispatcher in the reviewed path. MainActivity being exported as a launcher/home entry therefore does not itself prove arbitrary vehicle-command execution.

`onStart` requests GPS updates only when ACCESS_FINE_LOCATION is granted, interval 5000 ms, minimum distance 0; onStop removes that listener and marks inactive. Weather location lifecycle is distinct from a hypothetical always-on background tracker. Other services must be assessed separately.

`onPause` cancels delayed UI callbacks/marks foreground state false and releases or pauses embedded views. `onDestroy` closes view helpers, shuts down executors/handler threads and weather worker. Supporting onResume Java has invalid break/control-flow artifacts; do not claim every lifecycle branch correct from that decompilation alone.

## 2. Activity results / external file selection

REA MainActivity.onActivityResult requires RESULT_OK, nonnull Intent and nonnull data URI before dispatch. Codes 50/51 and 54 schedule background import tasks; 56 consumes previously selected image identifier F and clears it before importing; 44/45 call k8.K0; 49 imports screensaver; 41/42/43 choose current/day/night wallpaper. The method does not directly install arbitrary inbound APKs. File parser validation belongs to downstream tasks, reviewed by the separate data/files audit.

`startActivity` and `startActivityForResult` overrides close embedded/temporary navigation state before leaving; they delegate to Android Activity after housekeeping. They do not add an unverified arbitrary download/run operation.

## 3. App drawer / package removal

Source i0 builds a drawer by querying MAIN+LAUNCHER activities, excludes own package, supports favorite_apps and app information. Long-click checks ApplicationInfo.FLAG_SYSTEM; uninstall choice is shown only for non-system apps (failed lookup defaults to treating as system). The uninstall callback uses standard `android.intent.action.DELETE` with a `package:` URI, rather than directly invoking a silent deletePackage API. If launching uninstall fails, it shows an explanation and opens app info.

This is user-selected app management; requesting REQUEST_DELETE_PACKAGES in manifest is not evidence of silent uninstall. Android/firmware still controls confirmation and authority.

## 4. External navigation / tasks / privileged APIs

MainActivity.Q handles internally selected Intent launching, clears embedded state, and catches ActivityNotFoundException/SecurityException with user-facing messages. `MainActivity.Z` selects an app slot and launches/embeds corresponding component; no externally supplied arbitrary component path identified from onNewIntent.

REA k8.Z intentionally restarts the selected navigation package in external fullscreen mode: sets display 0, tries hidden setLaunchWindowingMode(1), enumerates stack/task metadata through IActivityManager, removes matching navigator tasks, attempts forceStopPackage(selectedPackage), falls back to killBackgroundProcesses(selectedPackage), then launches that specific component with MAIN+LAUNCHER and NEW_TASK|CLEAR_TASK. It records diagnostics and delayed observations at 350/1100/3000 ms.

IMPORTANT decompiler correction: Java output in k8.Z misleadingly appears to remove every string-named task because a continue was lost. Original DEX offsets 0x0122→0x0125→0x0150 SKIP removeTask when package comparison fails; matching package reaches removeTask at 0x0132. Do NOT report an all-app-kill bug. Removal/stop is limited to selected package in this branch. Actual success requires privileged firmware APIs and permissions; exceptions are logged.

`eh` implements split-screen using IActivityManager startActivityFromRecents, resizeDockedStack/resizeStack/dismissSplitScreenMode/getStackInfo; `NativeNavigationWindow` uses Android ActivityView and calls release in close. This explains many stack/window permissions. OEM/Android-version compatibility is not verified.

## 5. Wi-Fi / Bluetooth / brightness / audio

Source pi.d0 directly toggles WifiManager.setWifiEnabled or BluetoothAdapter.enable/disable, accounts for transitional states and catches Throwable returning null on failure. DEX MainActivity.g0 0x0902..0x0920 confirms fallback to Android WIFI_SETTINGS or BLUETOOTH_SETTINGS when helper returns null. Therefore helper's Java Bluetooth path reconstruction oddity should not be interpreted as a guaranteed failure; DEX is authoritative.

Brightness UI `ya.b`, case 0 checks Settings.System.canWrite before writing screen_brightness_mode=0 and screen_brightness=1..255, plus own window brightness and preference last_brightness. Helpers open MANAGE_WRITE_SETTINGS/overlay permission screens when capability missing. These are app/firmware-dependent settings, not evidence of silent privilege escalation.

Volume methods use AudioManager STREAM_MUSIC: k8.p0 adjusts volume/mute; a.run sets stream volume; x7 toggles mute. Media dispatch uses media session/AudioManager keys; no microphone recording APIs were found in the reviewed direct DEX invoke inventory.

## 6. Action dispatcher / voice integration

MainActivity.g0 is large and Java reconstruction unavailable, so original DEX `main-g0.dex.txt` was inspected. It routes internally configured actions/prefixes app:, split:, x:, widget:, media/volume, Wi-Fi/BT, theme, navigation and vehicle operations. This method is not an exported Android component, and a string appearing here is not an externally callable command by itself.

Voice action checks selected navigator component, checks RECORD_AUDIO permission ON THE NAVIGATOR PACKAGE through PackageManager.checkPermission, and sends action dd.call_alice with Intent.setPackage(selectedNavigatorPackage). It also logs that delivery is not confirmed. This does not show ReferencePanel requesting/using its own microphone. The manifest does not declare RECORD_AUDIO; string/checkPermission alone must not be labeled audio spying.

Vehicle actions in g0 include climate temp/fan/airflow, roof/curtain/trunk, mirrors/light/wipers and controls, routed through nj/other vehicle helpers. State/command safety validation is covered by the vehicle audit; the existence of action names alone does not establish support on every model.

## 7. Overlays / touch / status bar

Source q.a wraps Settings.canDrawOverlays. Climate overlay q uses TYPE_APPLICATION_OVERLAY=2038 with user setting gates. MainActivity.onStop may persist user-created picture/widgets in overlays at saved screen rectangles; it requires overlay capability, valid region state and activity not finishing/destroyed, and removes/refreshes overlay state on lifecycle transitions. This is powerful user-visible behavior, not proof of credential capture.

NavigationAssistService uses navigation controls/gesture strip and performGlobalAction BACK; parent REA callback onAccessibilityEvent is empty and manifest resource canRetrieveWindowContent=false. MainActivity.dispatchTouchEvent forwards a top-edge fullscreen gesture to vendor statusbar onTouchEventFromOther when available, else invokes expandNotificationsPanel reflectively. It catches/logs refusal. It uses current touch events for system shade UI; no InputManager.injectInputEvent call found in direct DEX inventory.

## 8. Additional radio exposure

LauncherApplication.onCreate dynamically registers jd receiver for the same radio action set, with no sender permission argument. REA jd.onReceive accepts only intents with NO package and NO component, normalizes action prefix and checks RadioCommandReceiver.a, then calls pd.c(context,action,10). This is an implicit-broadcast route to the same unauthenticated radio dispatch as the manifest receiver. The explicit/implicit routes should not be asserted to execute twice: dynamic receiver filters explicit targeting, and Android implicit broadcast restrictions affect manifest delivery.

## 9. Bounded negative inventory

Across original DEX direct invokes, no Runtime.exec, ProcessBuilder start, DexClassLoader, WebView/addJavascriptInterface, InputManager/injectInputEvent or AccessibilityNodeInfo direct usage was observed. Only PathClassLoader is present, in r4.u for firmware AdapterAPI JAR loading; vehicle audit owns its path details. System.loadLibrary loads bundled dhguard. Reflection includes Android task/statusbar/system properties and ECARX APIs; no reviewed reflection selector derives an executable/class payload from untrusted network data.

These are static/bounded negative findings, not proof of impossibility: reflection/indirect paths, native code and runtime dependencies must be separately considered.

## New material findings / practical assessment

- Confirmed expansion: unauthenticated implicit radio command receiver exists in addition to declared explicit entrypoint. Same impact class as prior radio finding.
- Confirmed functional risk: selected navigation restart may force-stop navigation and clear its task; user-trigger path can interrupt an active navigation session if privileged calls succeed. No remotely reachable route established.
- No new severe system-command execution/credential-theft finding established from this scope.
- Compatibility risk is material because application relies on hidden/vendor ActivityManager, ActivityView, statusbar and write-settings APIs; broad manifest permission requests do not guarantee authorization.

## Remaining unknowns

No device or emulator execution, no runtime permission/overlay enforcement checks, no click-by-click UI review, no confirmation on specific head-unit firmware. MainActivity.O/P/g0 and k8.u0 include decompilation failures; g0 critical sensitive branches were reviewed in DEX, but not every UI rendering branch. PendingIntent/media specifics use prior surface audit. No exploit validation performed.

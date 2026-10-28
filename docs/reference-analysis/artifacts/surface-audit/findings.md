# REA surface audit — ReferencePanel 1.0.2

Target SHA-256: 24ab6a3199655531ad80a1d651b961971896fad1715d93799f2bacb4ac93965c
REA 6.8.0 / JADX headless MCP 0.7.1 pinned engine SHA-256 6e5eacf500b64292bfb73c49797c1958f6ee44646e43e868039ae7feb573ff75. Static analysis only; target never executed.

## Confirmed: unauthenticated exported radio command receiver

The decoded manifest (existing ../manifest.txt) declares org.reference.panel.RadioCommandReceiver exported=true with no permission. Its onReceive method takes intent action, strips the org.reference.panel. prefix via th.C, checks a fixed radio command set and dispatches pd.c(context, command, 10). It does not check sending UID/package/signature or caller permission. REA evidence ev_06cce56f8273ac58954b90a64a01f5de64f5cf772d1ad34172493b1520d62339, radio-onReceive.json.

pd.c confirms real command effects rather than merely string names: initializes radio adapter, returns/retries if not ready, then performs pause/play/seek/up/down/next/previous, and RADIO_FAVORITE reads current station and calls k8.I0(context, new kd(...)) to save it in favorites. Evidence ev_6c9acf1ea7765338898cc63d10abef33c1da524b03ff33b7c90530a59f4a19fd, pd-c.json. JADX warns it failed to restore string switch perfectly and has type inference warnings; the original DEX pd-disassembly.txt corroborates dispatch and call endpoints.

Security inference: another local app can attempt explicit broadcasts to control radio and alter favorites using the launcher's access. This is a local integrity/availability exposure, not evidence of remote access, arbitrary code execution, or vehicle safety control. Actual effect depends on receiver enabled state, OS broadcast delivery and a ready compatible radio adapter; no runtime reproduction performed. Suggested remediation: permission-gate trusted integration callers with signature protection, or disable export if cross-app integration is unnecessary.

## Confirmed: accessibility event handler is empty

NavigationAssistService.onAccessibilityEvent has an empty body. Evidence ev_f748c387043431ed728fa19b1f24e42aab9a570cd3ab4abef8c7f6bd48409e60, accessibility-event.json. Existing decoded accessibility resource sets canRetrieveWindowContent=false. Existing DEX has performGlobalAction invocations used for navigation. This supports a navigation-assistance purpose for this path and does not establish broad device privacy guarantees.

## Confirmed: music notification parser applies narrowing and PendingIntent safeguards

MusicNotificationListener.a(StatusBarNotification) accepts notifications with media-session token, MediaStyle template, or transport category plus recognized actions, excluding flags&512. It extracts title/text/icon and token, and stores recognized action PendingIntents only if creator package equals notification package and !pendingIntent.isActivity(). Evidence ev_32898f15eae20958554617bd4842b6a5418f4727e299de9589f4bddee981c421, notification-a.json. This is positive evidence against describing this method as arbitrary notification harvesting/action invocation. The Android notification-listener permission is still sensitive; other code paths were not exhaustively audited for storage/network transmission.

Both NavigationAssistService and MusicNotificationListener are exported but protected by Android BIND_ACCESSIBILITY_SERVICE and BIND_NOTIFICATION_LISTENER_SERVICE respectively. Exported alone does not make these services callable by ordinary apps.

## Confirmed: MainActivity external intent path is limited to home UI selection

onNewIntent sets its new intent and, if OPEN_HOME boolean or HOME category is present, calls j(), clears two booleans and refreshes UI through e0(). Evidence ev_0a0d9eb291c8a79d17ae672a23d41003a50e751ff3d38f5ec630650762209f1f, main-onNewIntent-recovered.json. No arbitrary URI launch, APK install or car command is dispatched in this method. Existing onCreate DEX also reads OPEN_HOME only to clear its onboarding/UI boolean. This is not a complete audit of every MainActivity callback.

## Known boundaries

The update provider/receiver, boot receiver and automation/playback services are declared exported=false. This reduces direct component entry points; it does not prove all granted URI handling is safe. No active device, emulation, IPC fuzzing, or physical car testing was performed.

An initial MainActivity REA call hit cleanup_incomplete EACCES on a sandbox-owned /proc environment read; that diagnostic is preserved in main-onNewIntent.json and is not analysis evidence. After inspecting failure resources and switching to the available per-command permission profile, the corrected call completed and produced the evidence above. All spawned analysis command sessions in this audit were observed to exit.

# THT Launcher 0.3.76 — local release build

Source 73b7bb0e1d55f5a908eb58c8228112c9a556a149. GitHub Actions 75/76 could not start because of an Actions budget limit. This APK is built locally, not from successful CI. Signing certificate is verified against the platform certificate recorded in the owner's report; no signing keys are included.

Owner-device evidence (derived only): SX11-LA/IHU730P Android 9; physical 1920×720, app metrics 1760×720; overscan THT=0; estimated OEM reservation 160px, THT rail 120px. Secondary-display capability=true and one embedded display existed; report did not capture the requested failed pair/activity. Full raw owner report stays local.

Changes: automatic split on Coolray uses display-0 native split. Generic auto still uses supported hosted embedding. Explicit hosted remains experimental. External apps no longer have the full THT rail over their content: compact floating Home/Back and gesture controls remain. Main THT still shows its rail. Stock-mode isolation still suppresses all THT navigation overlays.

Gesture surface: bottom strip is 24dp above the edge to avoid OEM navigation; swipe up on the floating Home/Back buttons to return THT, sideways for Back. Long-press still drags the buttons. This does not intercept map/video swipes everywhere on screen.

Use Cài đặt → Dock → Cách chia hai ứng dụng → Tự động (Coolray: chia đôi hệ thống) or Chia đôi hệ thống. Native split needs app multi-window support and retains its system divider. An existing home preview can still use a secondary display; split routing is distinct from preview/app-internal redirects. No claim that OEM secondary-display restrictions were removed. No vehicle/runtime gesture validation for this build; local test results are saved separately. No merge to main or auto-update publication.

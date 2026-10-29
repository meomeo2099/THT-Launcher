# OEM isolation, task reuse and visible split close

Owner reports external-app letterboxing, no discoverable split close, embedded apps restarting on return and THT overlays interfering with OEM HOME.

Changes:
- Explicit OEM HOME handoff persists stock_mode. Cancel delayed fullscreen repairs, restore THT-owned policy and overscan, hide floating dock, remove accessibility overlays and suppress their recreation/events until returning to THT. Failed OEM launch rolls back. Opening THT resumes its UI.
- Remove positive global overscan on external launch; reserveForExternal now only clears THT's prior reservation. This removes one source of duplicated OEM/THT insets; actual firmware letterboxing still needs validation. App content can extend under overlay rail on firmware that has no native inset.
- Visible × on the narrow split gutter. Menu remains for swap/close.
- Hosted swap mirrors the container order/weights without detaching ActivityViews or deleting tasks. Home retains hosted session; reopening same pair raises existing task. Native splits still close before returning Home.
- Ignore stock_mode/nav_bar_overscan runtime preferences for appearance rebuild, avoiding map preview recreation after temporary window changes.

Limits: Android may reclaim stopped tasks. Opening an embedded app as an independent fullscreen task still uses prior handoff cleanup and can recreate its activity; no promise of state preservation across display transfer or process death. OEM isolation does not modify firmware or power services. APK/runtime results saved on artifact branch; no physical Coolray/Vietmap validation.

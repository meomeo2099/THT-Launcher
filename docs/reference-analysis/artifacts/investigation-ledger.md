# ReferencePanel APK investigation ledger — updated after extended review

Static investigation of user-supplied reference.apk; no APK execution, modification, actuator invocation or app-endpoint contact.

Target SHA256: 24ab6a3199655531ad80a1d651b961971896fad1715d93799f2bacb4ac93965c
REA6.8.0/JADX0.7.1; both native ARM64 and ARMv7 reviewed.

Current synthesis: deep/review-notes.md (Vietnamese).
Scope and counts: deep/review-summary.json.
Full REA evidence index: evidence-index.json.

Deep reports:
- deep/native/findings.md
- deep/vehicle/findings.md
- deep/automation/findings.md
- deep/data-network/findings.md
- deep/ui-system/findings.md
Earlier complementary reports:
- update-audit/findings.md
- surface-audit/findings.md
- region-audit/findings.md

Corrections vs earlier summary: geography/system-locale gates exist in addition to device exemption; both native ABIs now covered. k8.Z only removes matching navigator tasks (Java output lost continue); no kill-all claim. Native probe has zero direct incoming static references; active Guard.offset uses hh.a. Source Java is a derived representation with13unavailable methods; original DEX retained.

Main conditional concerns: AOSP public development key signing; Guard non-GENUINE shifts setter functionID; unauthenticated explicit/implicit radio receiver; fingerprint-only SourceStamp self-check; unbounded pre-cap metadata buffering; aggregate font-ZIP memory; stale delayed automation callbacks. Device/firmware/runtime validation remains explicitly unknown.

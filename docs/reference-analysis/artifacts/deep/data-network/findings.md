# Detailed data/network/file/geographic review

Target SHA256: 24ab6a3199655531ad80a1d651b961971896fad1715d93799f2bacb4ac93965c. REA6.8/JADX0.7.1. No APK execution, modification or contact with application endpoints. REA Evidence and supporting same-engine full source/original DEX are distinguished below.

## Geographic restriction: correction to earlier device-exemption-only review

There is real geographic AND system-language gating. The earlier pi.b/pi.w review described only the signed device exemption, not the full restriction system.

- bf.a first evaluates pi.b (signed cached exemption with optional expiry). Accepted exemption returns no restriction.
- Otherwise bf.b selects first SYSTEM locale, fallback Locale.getDefault. Language must be ru/be/kk. This is not merely the app-language setting. Other languages cause LANGUAGE restriction immediately, regardless of GPS.
- With permitted language, cached MODE_PRIVATE panel/region_locked_gps causes LOCATION restriction. Its default is false: lack of fresh GPS does not itself immediately create a location lock; persisted prior state still applies.
- MainActivity.e renders a full-size clickable/focusable restriction overlay with reason and device code. Picture-overlay creation also requires bf.a==null. This proves UI gating, not that every background service stops.
- assets/geo/allowed.txt labels offline polygons as Russia, Belarus, Kazakhstan and Luhansk/Donetsk regions. This reports asset labels, not independent territorial/policy verification. o.run reads the asset through l0.v; l0.e tests polygon containment including holes.
- x6.onLocationChanged synthetic branch2 ignores missing polygon data, mock-provider fixes, reported accuracy>300m and0,0. Missing accuracy is accepted by this filter. Inside allowed polygons (excluding holes), or less than approximately5km from any edge, it clears lock/counter immediately. Outside, it locks only after>=3 qualifying fixes AND>=60000ms from first outside fix. Uses Location elapsedRealtimeNanos. On change, saves region_locked_gps and refreshes UI.
- Exact hysteresis confirmed in original DEX offsets01c3–0219. Java has duplicated/misreconstructed branches; especially its displayed equality change check is wrong. Do not infer a bug from that malformed Java.
- MainActivity.e refreshes exemption list after10min while restricted,24h otherwise, or negative wall-clock elapsed difference. l.run verifies downloaded detached signature before caching. pi.b itself checks cached list and device LocalDate expiry inclusive. This does not authenticate device clock correctness; no remote signature bypass demonstrated.

REA evidence:
bf.a region-gate.json ev_dad6294e2d4c57bef1f75417662c2928fe240f9eee433e8802babda9f91e6fb1
MainActivity.e region-ui.json ev_0875a5e5f52a5d15221cf64fbaf48f3d826f9221c1496bcf169e877a69ed012f
x6.onLocationChanged location-change.json ev_3b2c8b9c9d10eaefed7688de3d0bd0033b1ba6780ecbef9e8e24e8c8d73934ef
Supporting: x6.dex.txt, deep/source/{bf,x6,ye,o,l0}.java, original geo asset. Earlier parent's pi.b/pi.w/l.run REA records cover exemption.

## Network and privacy coverage

All original DEX strings/direct callsites and exported source were searched for URL creation, HTTP, sockets, WebView, TLS overrides, dynamic loading and command execution. callsite-inventory.json preserves matching instructions. Only3 URL-connection creation sites observed: pi.k(unlock), l0.u(update), l.run(weather). URL encode/decode calls are not requests. Dynamic APK URL/mirror come from authenticated update metadata; redirects add unknown runtime hosts. Asset radio/source/font URLs inspected are attribution, not evidence of requests.

Observed hardcoded endpoints: GitHub reference-owner/ReferencePanel-releases update.json/.sig and unlock/unlock.txt/.sig; api.met.no/weatherapi/locationforecast/2.0/compact with lat/lon.

Weather vj.a formats coordinates to4decimals using Locale.US (~11m latitude granularity, not an exact privacy radius), then l.run sends HTTPS query with User-Agent ReferencePanel/0.5 example.invalid/reference-source/launch and Accept application/json. This discloses precise position to weather provider. No AndroidID/VIN/history/notification text is included in reviewed weather request. vj.e selects newest last-known gps/network/passive fix or requests single update; refresh on force, displacement>10km, age>30min or absent weather. Initial cache stale after3h.

MODE_PRIVATE panel stores weather_lat/lon floats, time/temp/symbol. Region polygons are checked offline. Unlock fetch downloads full signed list without device token in request URL. Device token is displayable/copyable. Observed ClipboardManager calls write text; no clipboard-read call found.

No POST/upload/telemetry/TLS-trust-all/socket/WebView/mic/camera/SMS/contacts call was observed in these scanned DEX paths. This is a bounded static finding, not proof that all runtime/native/firmware-delegated behavior is harmless. No traffic captured.

REA vj.a weather-request.json ev_34f38d051e50b7025a5502a11ce2446f504157f0d630e922aa2af8ffb1a3da41; parent's rea-background-run.json contains l.run weather branch. Supporting vj.java/r0.java/pi.java + DEX.

## Local database and retention

kh is ordinary SQLiteOpenHelper for stats.db. Supporting original DEX/full source establish schema; REA stats-schema.json failed and is NOT evidence of success.

- trips: timestamps, start/end odometer, distance, drive/idle seconds, max speed, consumption, fuel used.
- days: date, aggregate distance/trips/drive/idle/fuel.
- voltage: timestamp, voltage, engine state.
- refuels: timestamp, odometer, percentages before/after, liters, full/manual.

No route-coordinate columns in these definitions. Timestamps and vehicle measures still reveal driving patterns. Default app-private SQLite; no application-level encryption observed in helper. OS filesystem encryption unknown. Manifest allowBackup=false reduces normal Android backup exposure, not root/firmware access.

ni.b checkpoints open trip JSON panel/stats_open_trip approximately every30s. ni.a purges voltage older than30days, checks at6h cadence. No automatic trips/days/refuels expiry observed in reviewed deletion paths; explicit UI deletion exists. Reviewed kh queries bind parameter values instead of concatenating user SQL; no SQL injection observed in this bounded review.

## Settings ZIP backup/import

u2.e stages to cache then replaces with rollback; input is selected content URI. <=100 ZIP names, duplicates/directories/unsupported entries rejected; only settings.json and allowed image basenames. File writes use basename inside staging, not arbitrary entry path. No ZipSlip observed. Streaming caps32MiB/entry,256MiB total,settings.json4MiB. Backup identity app=ReferencePanel/version1. Preference allowlist pi.e0 + type checks; <=20000settings,key200chars,string16384chars,set20000members/member500chars; numeric checks present (some Java float branches malformed).

Images bounds-decode: dimensions1..8192 and<=24MP. Existing files/preferences saved for rollback; temp cleaned. Export ordinary ZIP/JSON/images, no encryption/authentication observed; recognized settings can include automation/component selections, excludes stats.db/arbitrary files. pi.e0 does not admit region_pass_list/region_locked_gps/weather cache, so import does not directly restore those sensitive caches.

REA u2.e settings-import.json ev_4db49bfb3e5c61a964b413ae4ced6d022b6d29c04e589fdb65d696913ec38b38
u2.a settings-validation.json ev_c54f125e9141e6b42220e09780ab614b5d62e6105ea9e4a19180455e6ca62782
pi.e0 settings-allowlist.json (if successful); full supporting pi.java otherwise. Image filename regex in supporting u2 constructor matches only wallpaper/vehicle/widget PNG names.

## Icons/fonts/images and conditional memory risk

i7.d icon ZIP flattens names to lowercase basename; only manifest.json or [a-z0-9-]{1,48}.png. <=300accepted files,2MiB/file,40MiB accepted decompressed total,PNG dimensions1..1024. Sanitized generated private icon_sets directory, staging cleanup. No arbitrary-path extraction observed. Limits count accepted entries, not ignored entries or total decompression time; do not claim universal archive-bomb resistance.
REA icon-import.json ev_84a29f9ab608163677fcc2d0d670be26ea70ac1ea31b634b020a477f770d6f4e.

x3.f custom font reads raw/ZIP, validates font magic/Cyrillic coverage and Typeface.Builder, stores fonts/custom.part -> private fonts/custom.ttf. Reader x3.i bounds each read25MiB (supporting source/DEX). ZIP branch holds arrays for up to20 TTF/OTF entries before choosing one; NO aggregate decompressed-font memory bound. User-imported compressed ZIP can therefore retain nearly500MiB of font arrays plus compressed input and copy overhead before validation. Conditional LOCAL memory-exhaustion/availability risk requiring user import; not demonstrated RCE, network exploit or on-device crash. Ignored ZIP entries also lack aggregate work/deadline bound.
REA font-import.json ev_e381cf090332814dc63315eb9d9ea632339d1e0aa7c2daa5fbb11b406770be65, supporting x3.i for exact25MiB.

Parent l.run user image import copies<=25MiB to cache then ImageDecoder pa(default) downsamples max dimension1600/software allocation, PNG via AtomicFile with rollback. Other pa variants1600/512. This bounds decoded image on reviewed paths. Android codec/font engine vulnerabilities not tested or established.

## Update provider

UpdateApkProvider.openFile only mode r and always cacheDir/update.apk; URI path never becomes file path. Query exposes ReferencePanel.apk and length, insert unsupported, update/delete no-op. Manifest non-exported/grantUriPermissions; installer gets read grant as previously audited. No arbitrary file read/path traversal observed.
REA update-provider-open.json ev_1413caefaa757e19e311d6522f27ab18c64211b9435e3a304d1717f7214c7a66.

## Limits

No APK runtime, endpoints, device permissions, external installer/radio/firmware, memory failure or OS encryption tested. Static negative results bounded to inspected class/calls. Exported supporting corpus has8class errors/71warnings from parent, though original DEX covers selected sensitive branches. REA source-status completeness does not imply perfect decompilation semantics. Any failed REA JSON is kept as a diagnostic, not counted as evidence.

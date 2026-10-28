# Deep vehicle integration review — ReferencePanel 1.0.2

Target APK SHA-256: `24ab6a3199655531ad80a1d651b961971896fad1715d93799f2bacb4ac93965c`.

Scope: vehicle adapters, command routing, range validation, state feedback, connection lifecycle, and Guard impact. APK and vendor SDK were never executed. Findings combine actual REA 6.8.0/JADX 0.7.1 evidence (JSON files in this directory), original Dalvik disassembly, and the root's same-JADX full source export under `deep/source` (supporting decompilation, not falsely labeled REA Evidence).

## Architecture established

- `r4` is an ECARX reflection adapter. `r4.u()` first tries the APK/application classloader for `com.ecarx.xui.adaptapi.car.impl.CarImpl`, then uses fixed system JAR paths `/system/framework/AdapterAPI.jar`, `AdapterAPIImpl.jar`, `ecarx.jar`, `ecarxSignalAPI.jar`. These are system paths; this observed route is not a download-and-load external payload route.
- `r4.a()` instantiates `CarImpl(Context)`, optionally registers an `IConnectable.IConnectWatcher`, and calls `connect`. If the older connection interface is unavailable, it attempts direct adapter setup.
- `r4.g()` obtains `getICarFunction`, resets capability caches, and registers `IFunctionValueWatcher`. It tries a bulk property subscription and individual subscriptions as compatibility fallbacks. `p4.invoke()` processes supported/function/value callbacks and dispatches refresh notifications. Disconnect sets the active function object to null.
- `nj` is the higher-level controller. `nj.p()` tries reflective Android `android.car.Car.createCar` with a service connection as well as the ECARX adapter. It shows an unavailable/connection message if neither usable route exists. `nj.q()` cancels callbacks, increments generation counters, detaches the watcher and disconnects Android Car.
- ECARX setter wrappers are `r4.w(function,value)`, `r4.x(function,zone,value)` and `r4.B(function,zone,float)`. `r4.y` tries several zone conventions. Success means the SDK returned `Boolean.TRUE`; this is not independent proof of physical actuation.
- `nj.j()` reads Android Car properties and ECARX sensor fallbacks, validates plausibility for fuel/voltage/range, and merges adapter data. Its base readings are reused for about 10 seconds while changes can trigger faster refreshes. Generation counters reject stale asynchronous result delivery after reconnect/disconnect.

REA identities: `r4-class.json` ev_7e06b6010d237a565e87194b075c94b1238ad397b7f126625d38bb92c979c122; `r4-u.json` ev_ffca024ea852f201aac6c992566a8a811d2c6cd5577ff3c18b0b7525c39e1988; `r4-a.json` ev_ac5325ae86403508d4c9813779452cdfafeb0915f6b4197f338ec393755a3dbb; `r4-g.json` ev_142bcc89bc5a04978ed5be0df41caaf5fac90c80dea8d51cea53b0514b03177a.

## Functions mapped to actual setters

| Feature | Observed routing and constraints |
|---|---|
| Climate power, A/C, AUTO, recirculation, defrost, rear-window heat, SYNC, nozzle/wiper heating | `z2.e()` maps vendor function IDs. `u2.g()` restricts a known set of function/value combinations; ordinary on/off properties accept only 0/1. Recirculation and airflow use explicit enumerations. `r4.z()` routes validated climate writes through `u2.f()`; steering heat is handled separately. |
| Driver/passenger temperature | `r4.E()` rejects nonfinite inputs, `z2.h()` rounds to 0.5 degree and clamps 17–32. Driver/passenger zones are represented by 1 and 4. In `fj.b`, +/- uses the current validated reading and a 0.5 increment. If climate is known off, that button enables AUTO rather than immediately changing temperature. |
| Fan | `u2.f()` normalizes encoded/raw fan values, rejects unsupported top setting, maps AUTO to climate AUTO, and adapts vehicle-specific zone encoding. UI controls use 0–8. |
| Steering-wheel heat | `r4.D()` clamps requested level to off/1–3 and tries SDK overload compatibility. |
| Seat heating/ventilation | `rf` describes explicit function/zone combinations. `o0.b` cycles the known supported level range then off, sends an encoded value via `r4.x`, and temporarily caches the intended state. Some configurations expose three rear seating zones. |
| Sunroof | `r4.C()` clamps percent to 0–100. `r4.I()` requests tilt using vehicle-profile-specific overloads. `dj.b()` toggles open/tilt based on latest or short-lived intended state. |
| Sun curtain | `r4.A()` requests 0/100 percent with a boolean fallback and records intended state after SDK acceptance. |
| Ambient light | `dj`/`ij` choose Monjaro vs generic ECARX property profiles. RGB inputs are masked to 24 bits; the last nonzero color is remembered. |
| Drive modes | `pi.j()` resolves an explicit known mode model; `nj.o()` rejects unknown mode names, and `q0.b()` writes the mode property then caches the desired result for six seconds. |
| Engine start/stop setting | `ej.b()` toggles a current/cached boolean property. This refers to vehicle start-stop function, not a demonstrated ability to start an engine remotely. |
| ACC/G-Pilot mode switching | `nj.r()` imposes a six-second request cooldown. `a.b()` checks the SDK capability status is active, both modes are supported, and the current mode is one of the two before requesting the other. A delayed check compares the observed result around four seconds later and returns a nonconfirmation error on mismatch. |
| Tailgate, mirror folding, exterior lights, wiper service | `w7.a()` routes widget actions through `nj.h()`/`fh.b()`. The latter checks `r4.H(function)` support. `fg.b()` then sends the fixed function/zone/value or cycles a fixed list. |
| ESC Sport, lane keeping, parking assist, G-Pilot toggle, ECO hints, passenger display | `o2.o` maps these widget keys to vendor function IDs (many obtained via `Guard.id`). `nj.s()` normally routes through the capability-checked helper then a current-state toggle. Merely naming these widgets does not establish that any particular firmware grants control. |

REA setter evidence: `r4-A.json` ev_7726aba9875101ea261e35e8204addae19605bdf4daf4ed95dbd296b408cad1d; `r4-B.json` ev_1bd94bfc14728c996a7259d8f681482f855bc176b9f263fc0aef3afa5b0d77fd; `r4-C.json` ev_263f137e9abe2f014f195f51aed4b3dbadf60b38c5bc3a83c8dd57885419f2a6; `r4-D.json` ev_82d95e74975b975694bccae171cac8cbae4c230588b2ca2e3602256353f47c53; `r4-w.json` ev_b3e478418dfd030dd8e6aac4868d08b218440fd925a636fe37f2fa66227f7f42; `r4-x.json` ev_88fd1e6655afa7180732554d42df2332e1fe4947ca7d9061100d998d87a16ee0; `r4-y.json` ev_904fd40793070be5cacdd4781c4efd8166a7c31756ba7ad4773f1f60433f8fad; `r4-z.json` ev_08b3f7c6e5feed62afe88a1cd7d2025d47f5197a205ff5779b9da54cebe60266. `r4.E` source/range behavior is from full JADX plus DEX, because the separate REA method invocation failed cleanup; its error JSON is not successful evidence.

## Confirmed concern: non-genuine state shifts setter function IDs

`r4.b()` is the common reflective invocation boundary. It copies arguments, and when the target method name begins with `s`, the first argument is an Integer, and `Guard.offset(context)` is nonzero, it **adds that offset to the first argument** before invoking the SDK. The known setter wrappers use that first argument as vendor function/property ID.

This is confirmed in complete REA Java and original DEX, not inferred only from a string. It is more specific than saying a bad signature simply disables car features. For the shipped Guard implementation, non-GENUINE resolves to its nonzero nominal-broken value. Some other features are separately gated, but this adapter itself does not fail closed at this boundary.

Operational consequence is conditional: a modified function ID could be rejected, interpreted as an unavailable property, or map differently on a particular firmware. No physical misoperation has been observed, and no claim of remote vehicle compromise follows. The precise firmware SDK/property table must be inspected or safely tested to decide impact. Recommendation: reject the setter with a clear integrity error instead of altering an actuator identifier.

REA `r4-b.json`: ev_b7e26298858e4400726aa8e539a112d3220eccf88b0959b8470e2730afee110a. Source `deep/source/r4.java`, method `b`; original `r4.dex.txt`.

## Safeguards present, and limits

Observed safeguards include connection checks (`nj.b`), support checks on several sensitive function routes (`fh.b`), explicit known feature/value validation (`u2.g/f`), temperature/percentage/heat-level bounds, serialized command executor, exception-to-failure handling, reconnect generation counters, and cruise capability/current-mode/readback checks.

However, the reviewed generic setter and direct widget dispatch paths do **not** contain a universal speed/gear/parking-brake interlock. Many physical safety constraints may be enforced by ECARX or vehicle firmware, which is external to this APK. Absence in these specific app paths must not be expanded into a claim that the vehicle lacks interlocks. No arbitrary caller-input function-ID API was established as exported; the previously found radio receiver should not be misrepresented as exposing all car controls.

Intended-state caching can make the UI optimistic. `nj.d/f` retain expected values for roughly six seconds (temperature/seat/drive/ambient) or 12 seconds (roof/curtain). `nj.a` merges those overrides into display data until expiry or reconciliation. The callback says SDK/vehicle did not confirm when the boolean result is false, but an SDK true alone is not a physical sensor confirmation. Cruise gets a separate delayed observed-mode check. Distinguish desired state, SDK acceptance, and physical state in any operator-facing safety assessment.

## Guard outside car setters

Original DEX and full source show `Guard.offset` also used in `b1` to stop selected app-autostart when nonzero, `z6` to derive a genuine boolean, and `ni` statistics setup. This is broader than one JNI method. The current review does not establish that every vehicle function is uniformly gated by the region unlock list; the actual common setter boundary contains the integrity-offset behavior described above.

## Data read scope

The vehicle stack reads climate state, temperatures, fan/airflow, seat heat/vent state, drive mode, sunroof/curtain position, start-stop setting, lighting and convenience states, cruise/G-Pilot support/mode, fuel level, voltage, estimated range and driving/statistical sensor values. `ni` is the local trip/fuel/statistics manager, not a general cloud telemetry transport; its storage/privacy details belong to the data review. No network upload was demonstrated from these adapter routes.

## Remaining uncertainty and analysis limitations

- No connected head unit, live ECARX SDK implementation, or ECU/CarPropertyManager permissions were available. Actual granted operations, property semantics per model, anti-pinch and motion interlocks remain unknown.
- The full source has R8 obfuscation and significant JADX warnings in `r4.f/g/v`, `nj.a/j`, and some synthetic callback classes. Some generated Java contains impossible/uninitialized variable artifacts. Conclusions above use simple complete methods and original DEX where possible; the large state-merging/sensor paths are architectural observations, not proven exact high-level reconstructions.
- `r4.g` complete source has branch reconstruction warnings; subscription fallback intent is supported by the original DEX strings/calls, but exact branch recovery should not be taken solely from malformed generated Java.
- A proposed additional controller REA batch was not executed: automatic approval review could not complete due a usage limit. This was a review-service failure, not a determination that the analysis was unsafe. No approval bypass was attempted. Existing successful REA evidence and the source/DEX review support this report; additional controller-method REA captures were therefore not created.
- No modifications, integrity bypass, actuator invocation, or APK execution were performed.

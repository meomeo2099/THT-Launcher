# Native library review: libdhguard.so (ARM64 and ARMv7)

## Scope and identity

Static review of **both** bundled native libraries in the supplied `reference.apk` (APK SHA-256 `24ab6a3199655531ad80a1d651b961971896fad1715d93799f2bacb4ac93965c`). No library was loaded or executed. REA 6.8.0 with GNU objdump 2.44 supplied assembly; REA's independent ELF export decoder supplied symbol inventories. GNU readelf and small read-only byte comparisons supplied supporting ELF/header/constant evidence, explicitly separate from REA evidence.

| ABI | Bytes | SHA-256 | Exported JNI functions |
| --- | ---: | --- | --- |
| arm64-v8a | 7,224 | `23126f1022e48b5147c6e4505a587b0d12300155361859f524c4fffe7b7827aa` | resolve, probe |
| armeabi-v7a | 5,340 | `e899f53971f62c0a75866c75e7918898f99003243132ebc8e5367d2672238c41` | resolve, probe |

The ARMv7 export values have the Thumb low bit set (`0x725`/`0x749`), while code addresses are `0x724`/`0x748`. ARM64 code addresses are `0xa08`/`0xa34`. This is normal ELF/Thumb addressing, not a discrepancy.

## Function semantics

### Guard.resolve(int)

The native function performs an **unsigned index bound check** for a 20-element table, loads the selected encoded 32-bit word, decodes it with a fixed XOR constant and returns it. Out-of-range indices, including negative signed Java integers interpreted as unsigned, return zero. The encoded table is byte-identical in both libraries (ARM64 file offset `0x580`, ARMv7 `0x480`). This is constant hiding/indirection; there is no evidence in this function of network communication, arbitrary code loading or input-event injection. Function sizes are 44 bytes ARM64 and 32 bytes ARMv7.

### Guard.probe(Context)

The assembly and JNI strings show the following sequence:

1. Get the supplied Context class and resolve `getPackageManager()` and `getPackageName()`.
2. Invoke PackageManager `getPackageInfo(packageName, GET_SIGNATURES)` (legacy flag `0x40`).
3. Read `PackageInfo.signatures`.
4. For each signature, invoke `Signature.toByteArray()`.
5. Hash certificate bytes with an internal SHA-256 implementation and compare the resulting 32 bytes with an embedded expected digest.
6. Return a match result or a nonzero failure/status value. A PackageManager exception is cleared and a failure value is returned; missing signatures also fails.

The helper following the JNI body implements block buffering, message padding, message schedule and 64-round SHA-256 compression. The SHA-256 round constants and characteristic rotate operations corroborate the algorithm. In the observed block copy path the length is limited to the smaller of available block capacity and remaining input, so this particular copy is not an unchecked copy of the entire certificate into a 64-byte buffer.

ARMv7 has the same lookup table and same stored digest bytes as ARM64, with analogous JNI access, SHA-256 processing and comparison. Its NEON comparison mask is the same. This establishes semantic agreement for the reviewed functions; no equivalence proof by execution was performed.

## Important inconsistency and reachability

The expected certificate digest in **both** native libraries is:

`ea793ae30214dad2074abbfcdb7ce8950b757d3e615d6fc1307da21fec515a94`

This equals the supplied APK's **ReferencePanel SourceStamp certificate** digest. However, `PackageManager.getPackageInfo(..., GET_SIGNATURES)` normally returns the **APK signing certificate**, whose digest for this APK is:

`c8a2e9bccf597c2fb6dc66bee293fc13f2fc47ec77bc6b2b0d52c11f51192ab8`

Consequently, on ordinary Android PackageManager semantics, this native certificate comparison would not match the supplied APK if invoked. This is a static inference from exact constant and signer identities, **not** an observed runtime failure. A modified vendor PackageManager could differ, and no such firmware was supplied.

The preexisting full DEX disassembly contains no direct invoke of `Guard.probe`. A new REA `trace_android_references` query also reports zero incoming using nodes and complete coverage of that static node inventory. The active Java `Guard.offset` method observed by REA instead calls `hh.a(Context)` to inspect SourceStamp certificate presence/fingerprint. The native `Guard.resolve` is referenced by the Java `Guard.id` wrapper. Therefore **do not describe probe as a proven active enforcement gate** merely because it is exported or present in the binary. Reflection/dynamic native callers remain outside the static search boundary.

This native routine also uses the legacy signatures field rather than modern SigningInfo/rotation history. It performs a certificate digest match, not a separate APK-content or SourceStamp cryptographic signature verification.

## ELF surface and hardening observations

- Both are ordinary little-endian ELF shared objects with only two defined exported dynamic functions. No `JNI_OnLoad` export is present.
- Imported dynamic symbols are CRT cleanup helpers, `memcpy`, `__stack_chk_fail`, and on ARMv7 `__stack_chk_guard`. Dependency declarations include libc, libdl, liblog and libm; a declared dependency does not prove its APIs are called.
- No `system`, `exec`, socket, HTTP, `dlopen` or `dlsym` symbol is present in these import tables. Reviewed JNI names are confined to Android package/signature APIs. This is a bounded negative observation, not proof of overall app safety.
- Both have non-executable `GNU_STACK` (RW), no writable-and-executable LOAD segment, GNU_RELRO, and BIND_NOW/NOW dynamic flags. These are static indicators consistent with NX stack, W^X segment separation and full RELRO; actual loader enforcement remains unobserved.
- Stack protector calls/checks occur in nontrivial native functions.
- ARM64 contains some BTI instructions, but the exported `resolve` and `probe` entries are not BTI-prefixed and no GNU property enables a complete BTI enforcement claim. Do not report blanket BTI/PAC protection.
- Android note declares API 28 and NDK r28c; compiler comment identifies Android Clang 19.0.1. These are build metadata claims, not authenticated build provenance.
- ARM64 load alignment is 16 KiB, ARMv7 4 KiB. No general device compatibility guarantee follows from alignment alone.

## Evidence

- ARM64 disassembly (preexisting): `../../rea-native-disassembly.json`, REA Evidence `ev_4a89691fdba6a68b7bcd6d4d0e856b7ad61bae35b2d0abd643be2656e636a335`.
- ARMv7 disassembly: `armv7-disassembly.json`, REA Evidence `ev_075db7500dcfb668873e976ae553bd1aebb9f9f3d25a1f7cb42b4b0a285f5174`.
- ARM64 export inventory: `arm64-exports.json`, REA Evidence `ev_8565bd9aee14c3f2c93b28b272af00fe8535aafdb7cb8b9ce6a69d8d2093e72c`.
- ARMv7 export inventory: `armv7-exports.json`, REA Evidence `ev_ac8a634d56d24b428b3924f71c20efe4b40b26b70ac0daee3a4bba5e5395f9dd`.
- Static incoming references to `Guard.probe`: `guard-probe-references.json`, REA Evidence `ev_f391195ecd33856b9a605b3964ed3d8d8d8cca3e35dc6cf5acfed3594382c89a` (zero using nodes; reflective/dynamic calls not covered).
- Java offset implementation (preexisting): `../../rea-guard-offset.json`, REA Evidence `ev_532f58d2571d1b25fa60f3242c036f9a68dcf51de96f9e8399b84ffdb3b00828`.
- Signature identities (preexisting): `../../rea-apk-signature.json`, REA Evidence `ev_c82e2c52f0448d3ae979e4615fae10386647b8611818f02d441e674a471cfa29`; separate Android apksigner verification stored in `../../signature.txt`.
- Supporting raw evidence: `arm64-readelf.txt`, `armv7-readelf.txt`, both `*-facts.json` and `*-strings.txt`. These are not REA Evidence records.

## Remaining limits

No native runtime test, emulator/vehicle interaction, fuzzing, symbolic-execution proof, or full decompiler pseudocode was used. Android framework calls are indirect JNI vtable calls; their attribution is corroborated by indices, exact descriptors and surrounding dataflow. No claim is made that all app behavior or firmware code has been examined. The library is small enough for the two exported functions and their helpers to be reviewed in both ABIs; this is a focused native review rather than a certification of correctness.

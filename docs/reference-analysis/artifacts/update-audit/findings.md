# ReferencePanel update audit — REA 6.8.0 / JADX 0.7.1

Target SHA256: 24ab6a3199655531ad80a1d651b961971896fad1715d93799f2bacb4ac93965c.
All observations static; APK never run. REA engine JAR SHA256 6e5eacf500b64292bfb73c49797c1958f6ee44646e43e868039ae7feb573ff75.

## Trust chain and checks

- `l0.l()` fetches HTTPS update.json (64KiB declared limit) and detached update.json.sig (8KiB declared limit), verifies raw JSON bytes before JSON parsing; only returns update metadata when versionCode > 16. Evidence ev_734a2c66893ebd70288ecbe5a829a921e6cf816ff4cedfd305fc4eebd7fa22e1, l.json.
- `l0.y()` extracts pinned public key from embedded X509 certificate, SHA256withRSA over exact supplied bytes, Base64 detached signature; verification exceptions return false. Evidence ev_22991340b4d033bc6043889359615fa9ed49337123b00864f9655ddb4bb00a02, y-jdk21.json. OpenSSL independently shows RSA4096, subject ReferencePanel. Extracted cert SHA256 ea793ae30214dad2074abbfcdb7ce8950b757d3e615d6fc1307da21fec515a94, same as APK SourceStamp signer previously verified by apksigner. This is a distinct key from APK's Android certificate.
- `l0.g()` downloader: declared size must be 1..209715200 bytes; download streamed and rejected when bytes exceed declared size; exact final size required; SHA256 must equal signed metadata; PackageManager parses APK and checks packageName equals installed application package and APK versionCode equals signed versionCode. Failed URL removes cached file and tries mirror. Complete smali fallback, not Java. Evidence ev_ab3bd02be9e14b9778ccf219c89fc944e9df72846fdf1e7d5d98bbdab44e5279, g.json. No application-level APK signer comparison seen in this method; normal OS update signer checks remain Android's responsibility.

## Transport and installation

- `l0.u()` URL.openConnection, HttpURLConnection, 15s connect /30s read timeout, redirects enabled, ReferencePanel/1.0.2 User-Agent. No explicit scheme/host allowlist in this method; APK URL/mirror are authenticated by signed metadata, so this alone is not arbitrary remote code execution. Evidence ev_3cecf9cf69205d49bbd51cb1c4bf3a1801d03582405f6b746bbbffefa6387049, u.json.
- `l0.r()` creates PackageInstaller full-install session, sets app package, streams verified cache file, fsync, commits to explicit internal UpdateInstallReceiver callback. Evidence ev_55b0e68a30e9f191747468d79adea41279f021cabb9a599bcdabfdaeea0d9bce, r-jdk21.json. Actual silent-install eligibility depends on OS privilege.
- `l0.s()` alternative explicit external installer com.teto.inputservice/com.teto.inputservice.autoboot.ApkInstallActivity, read-only grant of content://<package>.update/ReferencePanel.apk, explicit VIEW activity. Installer's actual identity/behavior not in supplied APK and unverified. Evidence ev_cc57fa8759bf741fda88b4e7380a871d5c1ad49de8359a04d8438b430bc621d5, s-jdk21.json.
- `l0.k()` update_auto preference defaults true; this establishes the preference default, not automatic installation behavior. Evidence ev_296d06da7c5b9c4408c0d0842bda1e6a32949515871cdd37455fb3ca8c5dbc0f, k-jdk21.json.

## Confirmed implementation weakness: size cap checked after buffering metadata

`l0.f()` reads network stream using pi.M() before checking byte-array length against caller cap. Thus 64KiB/8KiB cap does not bound network transfer/memory allocation. `pi.M()` builds ByteArrayOutputStream sized Math.max(8192,inputStream.available()), copies stream then calls toByteArray. A sufficiently large response can exhaust memory before verification/length rejection. This is a robustness/availability weakness; remotely exercising it requires controlling the authenticated HTTPS response path or its content. No bypass of RSA authentication follows from this finding.

Evidence l0.f ev_4b8448258933badde48fc28ad55e436bf3a9024021ac3f8d2f552f83784ec020 (f-jdk21.json), pi.M ev_f4826497bf7d9d8c9103376378247cb7fe4a80857e5671f4b69dbf48b4fb858e (pi-M.json).

Suggested fix: enforce byte budget while reading response, before writes/growth; stream with total <= limit and reject oversized Content-Length as an optimization. Enforce HTTPS URLs if desired; signed hash remains essential for integrity.

## Limits

No live update server contact, no APK installation, no dynamic tests. l0.g's body fell back to complete smali; its controls were read from branches and field accesses. No claim of exploit, signer bypass, server compromise, or malware. Java17 startup cleanup intermittently failed ownership checks; successful methods used Java21 or earlier successful exact-APK results. Failed result files are diagnostics and must not be cited as successful Evidence.

pi.u copy-loop corroboration: ev_eb36cc7081cf902c5cd376a1422d1c33807998d208e782c948870ce342abcdb3 (pi-u.json).

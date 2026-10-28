# Device-specific unlock gate (REA)

Static target ReferencePanel 1.0.2, SHA-256 24ab6a3199655531ad80a1d651b961971896fad1715d93799f2bacb4ac93965c.

pi.w(Context) reads Settings.Secure.ANDROID_ID (empty string if unavailable), hashes UTF-8 "ReferencePanel-region:" concatenated with it using SHA-256, takes first six bytes, formats %02X hex and groups with hyphens. This is a 48-bit device-derived display token, not a GPS region or VIN. Evidence ev_de5de4eb7f205915a01ed4a49e9fa82a7d8649a53f80424a6874e16706f6d7af, pi-w.json. JADX type warnings affect temporary list representation; original pi.txt DEX corroborates first-six-byte loop.

pi.b(Context) loads panel SharedPreferences region_pass_list, parses entries via line iterator and w(27), normalizes its device token to uppercase hex, and accepts only exact entry match with absent expiration or LocalDate.now() not after expiration (expiry inclusive). Missing cache/empty parsed list/no valid matching entry returns false. Expiry is checked against local device calendar date, not a trusted server clock. The signature download/cache-write path is in parent agent's l.run audit; b itself does not verify signature again. Evidence ev_1937ff26d68443eaf4a356c4950a378d114f8190027ee2e1b544118b14544502, pi-b.json.

w.b(Object), case 27: strips # comment suffix and surrounding whitespace, discards empty lines, splits whitespace. First token is uppercased and non-hex characters removed; exactly 12 remaining hex characters required. Optional second token is LocalDate.parse; invalid date rejects entry. No date means null expiry. Additional tokens are not consulted by this parser. Case 26 formats device token bytes as %02X. Evidence ev_c81416146f0727bf918e46a92c9ff794d1c1084f49cdf00464f52d5c00d13d84, w-b.json. No generated keys, cache changes or authorization bypass attempted.

Boundaries: establishes acceptance logic for cached device allowlist only, not all geographic restrictions or the full feature gate. No runtime behavior observed. pi.i and pi.j are unrelated app-label/model lookups and are not called by pi.b.

import json, re, pathlib, hashlib, zipfile, collections

base = pathlib.Path('docs/reference-analysis/artifacts')
out = base / 'deep'
apk = pathlib.Path('reference.apk')
cls = method = None
calls = []
for line in (base / 'dexdump.txt').read_text().splitlines():
    m = re.search(r"Class descriptor\s+: '([^']+)'", line)
    if m:
        cls = m[1]
    m = re.search(r'\|\[[0-9a-f]+\] (.+)', line)
    if m:
        method = m[1]
    if '|'+'' in line and re.search(r'\|[0-9a-f]+: invoke-', line):
        loc, insn = line.split('|', 1)
        calls.append({'class': cls, 'method': method, 'file_offset': loc.split(':')[0], 'instruction': insn.strip()})

patterns = {
 'network': r'Ljava/net/|Ljavax/net/|Landroid/net/',
 'reflection': r'Ljava/lang/reflect/|Ljava/lang/Class;\.(forName|getMethod|getDeclaredMethod|getField|getDeclaredField)',
 'dynamic_code': r'DexClassLoader|PathClassLoader|InMemoryDexClassLoader|Ljava/lang/System;\.(load|loadLibrary)|Ljava/lang/Runtime;\.(exec|load|loadLibrary)|Ljava/lang/ProcessBuilder;',
 'broadcast': r';\.(sendBroadcast|sendOrderedBroadcast|registerReceiver|sendStickyBroadcast):',
 'filesystem': r'Ljava/io/(File|FileInputStream|FileOutputStream|RandomAccessFile);|Ljava/util/zip/|Landroid/content/ContentResolver;\.(open|query|insert|update|delete)',
 'sqlite': r'Landroid/database/sqlite/',
 'preferences': r'Landroid/content/SharedPreferences',
 'location': r'Landroid/location/',
 'vehicle': r'Landroid/car/',
 'permission': r';\.(checkSelfPermission|requestPermissions|checkCallingPermission|enforceCallingPermission|canWrite|canDrawOverlays):',
 'app_management': r'Landroid/app/(ActivityManager|ActivityTaskManager)|Landroid/content/pm/(PackageManager|PackageInstaller)',
 'crypto': r'Ljava/security/|Ljavax/crypto/',
 'webview': r'Landroid/webkit/',
 'sms_contacts_camera_mic': r'Landroid/(telephony/SmsManager|provider/ContactsContract|hardware/Camera|hardware/camera2|media/AudioRecord|media/MediaRecorder)',
}
indexed = {k: [c for c in calls if re.search(p, c['instruction'])] for k,p in patterns.items()}
(out/'sensitive-call-index.json').write_text(json.dumps(indexed, ensure_ascii=False, indent=2))
summary = {k: {'call_instructions':len(v), 'caller_methods':len({c['method'] for c in v})} for k,v in indexed.items()}

warnings=[]
for f in sorted((out/'source').rglob('*.java')):
    text=f.read_text()
    errors=[{'line':i+1,'text':l.strip()} for i,l in enumerate(text.splitlines()) if 'JADX ERROR' in l or 'Method not decompiled' in l or 'Method dump skipped' in l]
    markers=len(re.findall('JADX WARN:',text))
    if errors or markers:
        warnings.append({'file':str(f.relative_to(out/'source')),'warning_markers':markers,'unavailable_or_error_markers':errors})
(out/'decompilation-coverage.json').write_text(json.dumps(warnings,ensure_ascii=False,indent=2))
with zipfile.ZipFile(apk) as z:
    inventory=[{'path':i.filename,'bytes':i.file_size,'compressed_bytes':i.compress_size,'sha256':hashlib.sha256(z.read(i)).hexdigest()} for i in z.infolist() if not i.is_dir()]
    (out/'apk-inventory.json').write_text(json.dumps(inventory,ensure_ascii=False,indent=2))
    for name in ['META-INF/com/android/build/gradle/app-metadata.properties','META-INF/version-control-info.textproto']:
        print(name,z.read(name).decode())

(out/'static-index-summary.json').write_text(json.dumps({'kind':'supporting DEX disassembly index, not REA Evidence','apk_sha256':hashlib.sha256(apk.read_bytes()).hexdigest(),'total_invoke_instructions':len(calls),'categories':summary,'classes_with_warning_markers':len(warnings),'files_with_unavailable_or_error_markers':[w['file'] for w in warnings if w['unavailable_or_error_markers']]},ensure_ascii=False,indent=2))
print(json.dumps(summary,indent=2))

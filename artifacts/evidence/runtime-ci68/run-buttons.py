import os,pathlib,subprocess,json
os.environ['ANDROID_USER_HOME']='/workspace/work/android-user'
adb='/workspace/.tht-env/android-sdk/platform-tools/adb';base=pathlib.Path(__file__).parent
results={}
for backend in ['native','auto']:
 for ratio in ['50','30']:
  name=backend+'-'+ratio
  p=subprocess.run([adb,'shell','am','instrument','-w','-e','backend',backend,'-e','ratio',ratio,'vn.tht.fixture/.ButtonsProbe'],capture_output=True,text=True,timeout=180)
  (base/(name+'.txt')).write_text(p.stdout+p.stderr)
  ok='INSTRUMENTATION_RESULT: result=PASS' in p.stdout
  results[name]='PASS' if ok else 'FAIL'; print(name,results[name],p.stdout,flush=True)
  (base/'summary.json').write_text(json.dumps(results,indent=2)+'\n')
  if not ok:raise SystemExit('FAILED '+name)

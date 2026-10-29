import pathlib,subprocess,time,json,os
os.environ['ANDROID_USER_HOME']='/workspace/work/android-user'
adb='/workspace/.tht-env/android-sdk/platform-tools/adb';base=pathlib.Path('/workspace/work/oem67/ci-final')
steps=[('host','HostProbe'),('widget','HostProbe'),('insets','HostProbe'),('stalereserve','HostProbe'),('climate','HostProbe')]
for case,probe in steps:
 subprocess.run([adb,'shell','rm','-f','/data/user/0/vn.tht.launcher/files/host-probe.json'],capture_output=True)
 file=base/('probe-'+case+'.txt')
 with file.open('w') as out:
  proc=subprocess.Popen([adb,'shell','am','instrument','-w','-e','scenario',case,'vn.tht.fixture/.'+probe],stdout=out,stderr=subprocess.STDOUT)
  seen=set()
  while proc.poll() is None:
   if probe=='HostProbe':
    try:
     p=subprocess.run([adb,'shell','cat','/data/user/0/vn.tht.launcher/files/host-probe.json'],capture_output=True,text=True,timeout=8)
     phase=json.loads(p.stdout)['phase']
     if phase not in seen:
      seen.add(phase);print(case,phase,flush=True)
      if phase in ['host50_settled','host30_resized','host_swapped','widget1200x500_same_display','settings_insets','host_with_rail','single_zone_climate']:
       pic=subprocess.run([adb,'exec-out','screencap','-p'],capture_output=True,timeout=8)
       (base/'screenshots'/(case+'-'+phase+'.png')).write_bytes(pic.stdout)
    except (ValueError,KeyError,subprocess.TimeoutExpired):pass
   time.sleep(.8)
 text=file.read_text();print(case,text,flush=True)
 if 'INSTRUMENTATION_RESULT: result=PASS' not in text:raise SystemExit('FAILED '+case)

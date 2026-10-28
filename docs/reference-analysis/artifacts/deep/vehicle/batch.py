import subprocess,os,pathlib,json
p=pathlib.Path('docs/reference-analysis/artifacts/deep/vehicle'); env=os.environ.copy();env.update(JAVA_HOME='/usr/lib/jvm/java-21-openjdk-amd64',REA_JADX_MCP_JAR='docs/reference-analysis/artifacts/jadx-headless-mcp-0.7.1-all.jar')
base=['/workspace/work/rea-tools/node_modules/node/bin/node','/workspace/work/rea-tools/node_modules/rea-agents/scripts/rea.mjs','inspect-android-method','reference.apk']
for method in ['u','a','b','A','B','C','D','E','J','w','x','y','z','g','q']:
 f=p/('r4-'+method+'.json')
 if f.exists():continue
 r=subprocess.run(base+['r4',method,'--json'],env=env,capture_output=True,text=True,timeout=180);f.write_text(r.stdout)
 try:
  d=json.loads(r.stdout); n=d.get('normalized_result',{});(p/('r4-'+method+'.java')).write_text(n.get('source',{}).get('text',''));print(method,d.get('evidence_id',d.get('code')),flush=True)
 except:print(method,r.stderr[:200],flush=True)

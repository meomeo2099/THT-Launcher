import os,subprocess,pathlib
p=pathlib.Path('docs/reference-analysis/artifacts/update-audit'); env=os.environ.copy();env['JAVA_HOME']='/usr/lib/jvm/java-21-openjdk-amd64';env['REA_JADX_MCP_JAR']='docs/reference-analysis/artifacts/jadx-headless-mcp-0.7.1-all.jar'
base=['/workspace/work/rea-tools/node_modules/node/bin/node','/workspace/work/rea-tools/node_modules/rea-agents/scripts/rea.mjs','inspect-android-method','reference.apk','l0']
for m in ['r','s','f','k']:
 r=subprocess.run(base+[m,'--json'],capture_output=True,env=env);(p/(m+'-jdk21.json')).write_bytes(r.stdout); print(m,r.returncode,flush=True)
 if r.returncode:print(r.stdout[:1000]);break

import subprocess,os,pathlib,concurrent.futures
out=pathlib.Path('docs/reference-analysis/artifacts/update-audit'); env=os.environ.copy(); env['JAVA_HOME']='/workspace/.tht-env/jdk'; env['REA_JADX_MCP_JAR']='docs/reference-analysis/artifacts/jadx-headless-mcp-0.7.1-all.jar'
base=['/workspace/work/rea-tools/node_modules/node/bin/node','/workspace/work/rea-tools/node_modules/rea-agents/scripts/rea.mjs','inspect-android-method','reference.apk','l0']
def go(m):
 r=subprocess.run(base+[m,'--json'],env=env,capture_output=True);(out/(m+'.json')).write_bytes(r.stdout);(out/(m+'.err')).write_bytes(r.stderr);return m,r.returncode
with concurrent.futures.ThreadPoolExecutor(max_workers=3) as ex:
 for x in ex.map(go,['l','y','g','u','f','r','s','k']):print(x,flush=True)

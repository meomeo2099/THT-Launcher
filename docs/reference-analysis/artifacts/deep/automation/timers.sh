export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
export REA_JADX_MCP_JAR=docs/reference-analysis/artifacts/jadx-headless-mcp-0.7.1-all.jar
REA_NODE=/workspace/work/rea-tools/node_modules/node/bin/node
REA_ENTRY=/workspace/work/rea-tools/node_modules/rea-agents/scripts/rea.mjs
APK=reference.apk
for spec in 'p1 b' 'o1 run' 'q1 run' 'b1 a'; do
 read -r cls method <<< "$spec"
 "$REA_NODE" "$REA_ENTRY" inspect-android-method "$APK" "$cls" "$method" --json > "docs/reference-analysis/artifacts/deep/automation/$cls-$method.json"
done

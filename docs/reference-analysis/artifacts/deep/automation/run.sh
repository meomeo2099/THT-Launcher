#!/bin/bash
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
export REA_JADX_MCP_JAR=docs/reference-analysis/artifacts/jadx-headless-mcp-0.7.1-all.jar
REA_NODE=/workspace/work/rea-tools/node_modules/node/bin/node
REA_ENTRY=/workspace/work/rea-tools/node_modules/rea-agents/scripts/rea.mjs
APK=reference.apk
"$REA_NODE" "$REA_ENTRY" inspect-android-method "$APK" org.reference.panel.BootReceiver onReceive --json > docs/reference-analysis/artifacts/deep/automation/boot-receive.json
"$REA_NODE" "$REA_ENTRY" inspect-android-method "$APK" org.reference.panel.BootPlaybackService onStartCommand --json > docs/reference-analysis/artifacts/deep/automation/playback-start.json
"$REA_NODE" "$REA_ENTRY" inspect-android-method "$APK" org.reference.panel.AutomationService onStartCommand --json > docs/reference-analysis/artifacts/deep/automation/automation-start.json

set -e
source /workspace/.tht-env/activate.sh
fixture_root=/workspace/work/split-review/fixtures
fixture_out=$fixture_root/vn.tht.fixture
javac -source 8 -target 8 -cp "$ANDROID_HOME/platforms/android-34/android.jar" -d "$fixture_out/classes" "$fixture_root/src/vn/tht/fixture/"*.java
jar cf "$fixture_out/classes.jar" -C "$fixture_out/classes" .
"$ANDROID_HOME/build-tools/34.0.0/d8" --min-api 28 --lib "$ANDROID_HOME/platforms/android-34/android.jar" --output "$fixture_out/dex" "$fixture_out/classes.jar"
"$ANDROID_HOME/build-tools/34.0.0/aapt" package -f -M "$fixture_out/AndroidManifest.xml" -I "$ANDROID_HOME/platforms/android-34/android.jar" -F "$fixture_out/fixture.apk"
(cd "$fixture_out/dex" && zip -q -u "$fixture_out/fixture.apk" classes.dex)
"$ANDROID_HOME/build-tools/34.0.0/apksigner" sign --key /workspace/work/map-handoff-fix/public-aosp-test-keys/platform.pk8 --cert /workspace/work/map-handoff-fix/public-aosp-test-keys/platform.x509.pem "$fixture_out/fixture.apk"
ANDROID_USER_HOME=/workspace/work/android-user adb install -r "$fixture_out/fixture.apk"

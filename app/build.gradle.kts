plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "vn.tht.launcher"
    compileSdk = 34

    defaultConfig {
        applicationId = "vn.tht.launcher"
        minSdk = 28          // Android 9 – đầu xe Coolray 2025 (API 28)
        targetSdk = 28       // giữ 28 để hành vi nền/overlay giống đầu xe
        // Số bản = số lần chạy CI, để bản sau luôn lớn hơn (tự cập nhật so sánh số này)
        val build = System.getenv("GITHUB_RUN_NUMBER")?.toIntOrNull() ?: 1
        versionCode = build
        versionName = "0.2.$build"
    }

    // Ký release bằng keystore từ GitHub Secrets (THT_SIGN_*); không có thì dùng khóa debug
    val signFile = System.getenv("THT_SIGN_STORE_FILE")?.let { file(it) }
    signingConfigs {
        create("tht") {
            if (signFile != null && signFile.exists()) {
                storeFile = signFile
                storePassword = System.getenv("THT_SIGN_STORE_PASSWORD")
                keyAlias = System.getenv("THT_SIGN_KEY_ALIAS")
                keyPassword = System.getenv("THT_SIGN_KEY_PASSWORD")
                    ?: System.getenv("THT_SIGN_STORE_PASSWORD")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = if (signFile != null && signFile.exists())
                signingConfigs.getByName("tht") else signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
    // App cài trực tiếp lên đầu xe (không lên Play Store) nên giữ targetSdk 28
    lint {
        checkReleaseBuilds = false
        abortOnError = false
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
}

// Đặt tên file APK: THT-Launcher-<phiên bản>-<release|debug>.apk
android.applicationVariants.all {
    val variant = this
    outputs.all {
        (this as com.android.build.gradle.internal.api.BaseVariantOutputImpl).outputFileName =
            "THT-Launcher-${variant.versionName}-${variant.buildType.name}.apk"
    }
}

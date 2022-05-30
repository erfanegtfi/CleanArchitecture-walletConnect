import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.secrets_gradle_plugin") version "0.6.1"
}


android {
    compileSdk = AndroidConfig.COMPILE_SDK
    buildToolsVersion = AndroidConfig.BUILD_TOOLS_VERSION

    defaultConfig {
        minSdk = AndroidConfig.MIN_SDK
        targetSdk = AndroidConfig.TARGET_SDK
        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME
        applicationId = AndroidConfig.ID

        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName(BuildType.DEBUG) {
            isDebuggable = BuildTypeDebug.isDebuggable
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            buildConfigField("String", "SHARED_SECRET_KEY", gradleLocalProperties(rootDir).getProperty("SHARED_SECRET_KEY").toString())
        }

        maybeCreate(BuildType.STAGING)
        getByName(BuildType.STAGING) {
            isDebuggable = BuildTypeStage.isDebuggable
            isMinifyEnabled = BuildTypeStage.isMinifyEnabled
            buildConfigField("String", "SHARED_SECRET_KEY", gradleLocalProperties(rootDir).getProperty("SHARED_SECRET_KEY").toString())
        }

        getByName(BuildType.RELEASE) {
            isDebuggable = BuildTypeRelease.isDebuggable
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            buildConfigField("String", "SHARED_SECRET_KEY", gradleLocalProperties(rootDir).getProperty("SHARED_SECRET_KEY").toString())
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }


    packagingOptions {
        resources.excludes.add("META-INF/atomicfu.kotlin_module")
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    lint {
        // Eliminates UnusedResources false positives for resources used in DataBinding layouts
        isCheckGeneratedSources = true
        // Running lint over the debug variant is enough
        isCheckReleaseBuilds = false
        // See lint.xml for rules configuration
    }


    testOptions {
        unitTests {
            isIncludeAndroidResources = true
//            isReturnDefaultValues = true
        }
    }
}

dependencies {

    implementation(Dependencies.MULTIDEX)

    implementation(Dependencies.KOTLIN_STANDARD_LIBRARY)
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.APP_COMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAINT_LAYOUT)
    implementation(Dependencies.ARCH_CORE_ANDROIDX)

    implementation(Dependencies.LIFECYCLE_RUNTIME)
    implementation(Dependencies.LIFECYCLE_VIEW_MODEL_KTX)
    implementation(Dependencies.LIFECYCLE_LIVE_DATA)
    implementation(Dependencies.LIFECYCLE_VIEW_MODEL_SAVE_STATE)
    implementation(Dependencies.LIFECYCLE_COMMON)
    kapt(Dependencies.LIFECYCLE_COMPILER)

    implementation(Dependencies.COROUTINE_CORE)
    implementation(Dependencies.COROUTINE_ANDROID)

    implementation(Dependencies.ACTIVITY_KTX)
    implementation(Dependencies.FRAGMENT_KTX)
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.ANDROIDX_LEGACY_SUPPORT)


    implementation(Dependencies.GLIDE)
    kapt(Dependencies.GLIDE)
    kapt(Dependencies.GLIDE_COMPILER)

    //dagger
    implementation(Dependencies.dagger)
    kapt(Dependencies.daggerCompiler)

    implementation(Dependencies.CONVERTER_SCALAR)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_JSON)
    implementation(Dependencies.RETROFIT_XML)
//    implementation(Dependencies.RETROFIT_JAXB)
    implementation(Dependencies.OK_HTTP)
    implementation(Dependencies.OK_HTTP_LOGGER)

    implementation(Dependencies.ROOM_KTX)
    implementation(Dependencies.ROOM_RUNTIME)
    kapt(Dependencies.ROOM_COMPILER)


    implementation(Dependencies.TIMBER)
    implementation(Dependencies.DATA_STORE)
    implementation(Dependencies.EVENT_BUS)
    implementation(Dependencies.SWEET_ALERT)

    implementation ("org.jetbrains.kotlin:kotlin-reflect:1.5.31")
    implementation ("com.github.WalletConnect:kotlin-walletconnect-lib:0.9.8")
    implementation ("com.squareup.moshi:moshi:1.8.0")
    implementation ( "com.github.komputing:khex:1.1.0")
    implementation ( "org.java-websocket:Java-WebSocket:1.5.2")
    implementation ("com.android.support:customtabs:30.0.0")


    // android test
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("com.google.truth:truth:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
//    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.3.0")
    androidTestImplementation("android.arch.core:core-testing:1.1.1")
//    androidTestImplementation("org.mockito:mockito-core:3.9.0")
    androidTestImplementation("org.mockito:mockito-android:3.9.0")
    androidTestImplementation("io.mockk:mockk:1.12.2")
    androidTestImplementation("com.squareup.okhttp3:mockwebserver:4.9.1")
    androidTestImplementation("androidx.test:rules:1.4.0")
    androidTestImplementation("androidx.test:runner:1.4.0")
    debugImplementation("androidx.fragment:fragment-testing:1.4.1")

    // test
    testImplementation("junit:junit:4.13.2")
    testImplementation("com.google.truth:truth:1.1.3")
    testImplementation("android.arch.core:core-testing:1.1.1")
    testImplementation("org.mockito:mockito-core:3.9.0")
    testImplementation("io.mockk:mockk:1.12.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.1")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.9.1")
    testImplementation ("app.cash.turbine:turbine:0.7.0")

    testImplementation("org.powermock:powermock-module-junit4:2.0.9")
    testImplementation("org.powermock:powermock-module-junit4-rule:2.0.9")
    testImplementation("org.powermock:powermock-api-mockito2:2.0.9")
    testImplementation("org.powermock:powermock-classloading-xstream:2.0.9")
    testImplementation("org.robolectric:robolectric:4.6.1")
    testImplementation ("org.json:json:20180813")
}
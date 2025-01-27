plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    alias(libs.plugins.dagger.hilt.android)
    kotlin(libs.plugins.serialization.get().pluginId).version(libs.versions.kotlin).apply(false)
    id("kotlin-parcelize")
    alias(libs.plugins.de.mannodermaus)
    alias(libs.plugins.com.google.devtools.ksp)
}

android {
    namespace = "my.app.moviesapp"
    compileSdk = 35

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.all { it.useJUnitPlatform() } }

    defaultConfig {
        applicationId = "my.app.moviesapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }

}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.core.ktx)
    implementation(libs.androidx.junit.ktx)
    implementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.roomTesting)
    testImplementation(libs.coreTesting)
    testImplementation(libs.coroutinesTest)
    androidTestImplementation(libs.coroutinesTest)
    testImplementation(libs.paging)
    testImplementation(libs.junit.jupiter.api)
    androidTestImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.platform.launcher)
    testRuntimeOnly(libs.byte.buddy)


    // Hilt testing dependencies
    testImplementation(libs.hiltTest)
    kaptTest(libs.hiltCompiler)

    // Android test dependencies
    androidTestImplementation(libs.espressoCore)
    androidTestImplementation(libs.hiltNavFragment)
    kaptAndroidTest(libs.hiltCompiler)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)
    implementation(libs.ok.http)
    implementation(libs.navigation)
    implementation(libs.gson)
    implementation(libs.hilt.android)
    implementation(libs.gson.converter)
    implementation(libs.encrypted.shared.prefs)
    implementation(libs.navigation.hilt)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.kotlin.serialization)
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.compose)
    implementation(libs.compose)
    ksp(libs.androidx.room.compiler)
}
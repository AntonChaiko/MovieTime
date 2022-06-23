plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.5.30"
    id("dagger.hilt.android.plugin")
}

repositories {
    google()
    mavenCentral()
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.packageName
        minSdk = Config.minSDK
        targetSdk = Config.targetSDK
        versionCode = Config.versionCode
        versionName = Config.versionName


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String","API_KEY","\"GpaAWioxfKujyRzgLjcKmVdaVHAMNlfm\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation(Dependencies.Core.coreKtx)
    implementation(Dependencies.Core.appCompat)

    implementation(Dependencies.Design.material)
    implementation(Dependencies.Design.constraint)

    implementation(Dependencies.Lifecycle.lifecycleKtx)

    // Navigation
    implementation(Dependencies.Navigation.navFragment)
    implementation(Dependencies.Navigation.navUi)

    //Serialization
    implementation(Dependencies.Kotlin.serialization)

    //Hilt
    implementation(Dependencies.Hilt.android)
    implementation(Dependencies.Hilt.navigation)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    kapt(Dependencies.Hilt.compiler)

    // Room
    implementation(Dependencies.Room.ktx)
    implementation(Dependencies.Room.runtime)
    implementation(Dependencies.Room.paging)
    kapt(Dependencies.Room.compiler)

    // Retrofit
    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.retrofitConverter)
    implementation(Dependencies.Retrofit.gson)

    //OkHttp
    implementation(Dependencies.OkKHttp.okhttp)
    implementation(Dependencies.OkKHttp.okhttpLoginInterceptor)

    //tests
    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.jUnitExt)
    androidTestImplementation(Dependencies.Test.espresso)
    androidTestImplementation("com.google.truth:truth:1.1.3")
    testImplementation("com.google.truth:truth:1.1.3")

    //Paging
    implementation ("androidx.paging:paging-runtime-ktx:3.1.1")

    implementation ("com.github.bumptech.glide:glide:4.13.0")

    androidTestImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")

}
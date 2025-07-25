plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.kosanku"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.kosanku"
        minSdk = 35
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

    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("androidx.recyclerview:recyclerview:1.4.0")
    implementation("com.github.denzcoskun:ImageSlideshow:0.1.0")
    // build.gradle (Module: app)

//    implementation('androidx.appcompat:appcompat:1.6.1')
//    implementation('androidx.constraintlayout:constraintlayout:2.1.4')
//    implementation('com.google.android.material:material:1.12.0')
//    implementation('androidx.cardview:cardview:1.0.0')

}
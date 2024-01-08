plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.faindsapplication"
    compileSdk = 34
    viewBinding{
        enable=true
    }

    defaultConfig {
        applicationId = "com.example.faindsapplication"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
//    repositories {
//        mavenCentral()
//        google()
//    }
}

dependencies {
    implementation("com.github.ybq:Android-SpinKit:1.4.0")
    implementation("com.android.volley:volley:1.2.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    // 이미지 넘길때 위치 표시해주는 효과
    implementation("me.relex:circleindicator:2.1.4")
    // 글라이드
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

}
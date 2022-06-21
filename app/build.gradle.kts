plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdk = Config.compile_sdk

    defaultConfig {
        applicationId = Config.application_id
        minSdk = Config.min_sdk
        targetSdk = Config.target_sdk
        versionCode = Releases.version_code
        versionName = Releases.version_name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf("room.schemaLocation" to "$projectDir/schemas")
            }
        }
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
        sourceCompatibility = Config.java_version
        targetCompatibility = Config.java_version
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Modules.util))

    //Legacy
    implementation(Legacy.legacy_support)

    //Room
    implementation(Room.runtime)
    kapt(Room.compiler)
    implementation(Room.room_ktx)

    //Coil
    implementation(Coil.coil)

    // Koin
    implementation(Koin.koin_core)
    implementation(Koin.koin_android)
    implementation(Koin.koin_android_compat)

    // Retrofit
    implementation(Retrofit.retrofit)
    implementation(Retrofit.converter_gson)
    implementation(Retrofit.adapter_coroutines)

    //Kotlin
    implementation(Kotlin.activity)
    implementation(Kotlin.core)

    //Design
    implementation(Design.appcompat)
    implementation(Design.material)
    implementation(Design.constraint)

    //Lifecycle
    implementation(Lifecycle.livedata)
    implementation(Lifecycle.viewmodel)
}
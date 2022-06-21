import org.gradle.api.JavaVersion

object Config {
    const val application_id = "geekbrains.ru.translator"
    const val compile_sdk = 32
    const val min_sdk = 28
    const val target_sdk = 28
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val util = ":util"
}

object Versions {

    //Legacy
    const val v4 = "1.0.0"

    //Design
    const val appcompat = "1.4.2"
    const val material = "1.6.1"
    const val constraint = "2.1.4"

    //Kotlin
    const val activity_ktx = "1.4.0"
    const val core = "1.8.0"

    //Lifecycle
    const val livedata_ktx = "2.4.1"
    const val viewmodel_ktx = "2.4.1"

    //Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val adapterCoroutines = "0.9.2"

    //Koin
    const val koin_version = "3.1.2"

    //Coil
    const val coil = "1.4.0"

    //Room
    const val room_version = "2.4.2"
}

object Legacy {
    const val legacy_support = "androidx.legacy:legacy-support-v4:${Versions.v4}"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
}

object Kotlin {
    const val activity = "androidx.activity:activity-ktx:${Versions.activity_ktx}"
    const val core = "androidx.core:core-ktx:${Versions.core}"
}

object Lifecycle {
    const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.livedata_ktx}"
    const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewmodel_ktx}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val adapter_coroutines =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.adapterCoroutines}"
}

object Koin {
    const val koin_core = "io.insert-koin:koin-core:${Versions.koin_version}"
    const val koin_android = "io.insert-koin:koin-android:${Versions.koin_version}"
    const val koin_android_compat = "io.insert-koin:koin-android-compat:${Versions.koin_version}"
}

object Coil {
    const val coil = "io.coil-kt:coil:${Versions.coil}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.room_version}"
    const val compiler = "androidx.room:room-compiler:${Versions.room_version}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room_version}"
}


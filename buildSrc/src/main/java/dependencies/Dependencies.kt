package dependencies


/**
 * @author by Abika Chairul Yusri on 9/14/2022
 */
object Dependencies {

    object AndroidX {
        private const val coreKtxVersion = "1.7.0"
        const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"

        private const val appCompatVersion = "1.4.0"
        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
    }

    object Build {
        const val recyclerView = "androidx.recyclerview:recyclerview:1.2.0-alpha03"
        const val constraint = "androidx.constraintlayout:constraintlayout:2.1.4"
        const val fragment = "androidx.fragment:fragment-ktx:1.5.2"
        const val activity = "androidx.activity:activity-ktx:1.5.1"

        private const val lifecycleVersion = "2.5.1"
        private const val lifecycleExtensionVersion = "2.2.0"
        const val lifecycleViewModelKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
        const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:$lifecycleExtensionVersion"
        const val lifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
        const val lifecycleCommonJava8 = "androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion"
    }

    object Coroutines {
        private const val coroutinesVersion = "1.6.4"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
    }

    object DaggerHilt {
        const val version = "2.43.2"
        const val hiltAndroid = "com.google.dagger:hilt-android:$version"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$version"
    }

    object Google {
        private const val materialVersion = "1.4.0"
        const val material = "com.google.android.material:material:$materialVersion"
    }

    object Glide {
        const val version = "4.12.0"
        const val core = "com.github.bumptech.glide:glide:$version"
        const val compiler = "com.github.bumptech.glide:compiler:$version"
    }

    object Okhttp {
        private const val okhttpVersion = "4.9.3"
        const val client = "com.squareup.okhttp3:okhttp:$okhttpVersion"
        const val log = "com.squareup.okhttp3:logging-interceptor:$okhttpVersion"
    }


    object Retrofit {
        private const val retrofitVersion = "2.9.0"
        const val core = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val gson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    }

    object Room {
        private const val roomVersion = "2.4.0"
        const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
        const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
        const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    }


    object SharedPrefs {
        private const val sharedPrefVersion = "1.2.0"
        const val sharedPrefKtx = "androidx.preference:preference-ktx:$sharedPrefVersion"
    }

    object Testing {
        private const val junitVersion = "4.13.2"
        const val junit4 = "junit:junit:$junitVersion"

        private const val junitAndroidExtVersion = "1.1.3"
        const val junitAndroidExt = "androidx.test.ext:junit:$junitAndroidExtVersion"

        private const val coroutinesTestVersion = "1.5.1"
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTestVersion"

        private const val truthVersion = "1.1.3"
        const val truth = "com.google.truth:truth:$truthVersion"

        private const val mockkVersion = "1.10.0"
        const val mockk = "io.mockk:mockk:$mockkVersion"
        const val mockkAndroid = "io.mockk:mockk-android:$mockkVersion"

        private const val turbineVersion = "0.7.0"
        const val turbine = "app.cash.turbine:turbine:$turbineVersion"

        private const val mockWebServerVersion = "4.9.3"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$mockWebServerVersion"


        const val hiltTesting = "com.google.dagger:hilt-android-testing:${DaggerHilt.version}"

        private const val testRunnerVersion = "1.4.0"
        const val testRunner = "androidx.test:runner:$testRunnerVersion"

        private const val espressoVersion = "3.4.0"
        const val testEspressoCore = "androidx.test.espresso:espresso-core:$espressoVersion"

        private const val multidexVersion = "2.0.1"
        const val multidex = "androidx.multidex:multidex:$multidexVersion"
        const val multidexInstrument = "androidx.multidex:multidex-instrumentation:$multidexVersion"
        
        private const val gsonVersion = "2.9.0"
        const val gson = "com.google.code.gson:gson:$gsonVersion"

        private const val roomVersion = "2.4.3"
        const val roomTesting = "androidx.room:room-testing:$roomVersion"

        private const val leakCanaryVersion = "2.4"
        const val leakCanary = "com.squareup.leakcanary:leakcanary-android:$leakCanaryVersion"
    }

    object Chucker {
        private const val chuckerVersion = "3.5.2"
        const val core = "com.github.chuckerteam.chucker:library:$chuckerVersion"
    }
    
    object Paging {
        private const val pagingVersion = "3.1.1"
        const val pagingRuntime = "androidx.paging:paging-runtime-ktx:$pagingVersion"
    }
    
    object Security {
        private const val sqlChiperVersion = "4.4.0"
        const val sqlChiper = "net.zetetic:android-database-sqlcipher:$sqlChiperVersion"

        private const val sqliteVersion = "2.1.0"
        const val sqlite = "androidx.sqlite:sqlite-ktx:$sqliteVersion"
    }

    object Lottie {
        private const val lottieVersion = "3.4.0"
        const val lottie = "com.airbnb.android:lottie:$lottieVersion"
    }
}
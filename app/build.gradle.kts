plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.khaznamovies"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.khaznamovies"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }


    buildFeatures {
        compose = true
        buildConfig = true
    }


    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    flavorDimensions += "api"

    productFlavors {
        create("live") {
            buildConfigField("String", "BASE_NETWORK_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField(
                "String",
                "AUTH_TOKEN",
                "\"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlZTY5YzZhYzA5MDM0YTI3N2NkZGIyYjhhNmYyNjFhNyIsInN1YiI6IjY0ZTYyZjRjOTBlYTRiMDBjNzM4ODVmNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.wfAILB4WSIrXDectu-MgnSXHf9AZPiea4HE_9TNPTMg\""
            )
        }
    }

    configurations.all {
        resolutionStrategy {
            force("androidx.lifecycle:lifecycle-viewmodel:${libs.versions.lifecycle.get()}")
            force("androidx.lifecycle:lifecycle-viewmodel-ktx:${libs.versions.lifecycle.get()}")
        }
    }
}

dependencies {
    // Core
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)

    // Compose BOM
    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.material.icons.extended)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
    androidTestImplementation(libs.compose.ui.test.junit4)

    // Navigation
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // Retrofit & OkHttp
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit.converter.scalars)
    implementation(libs.okhttp.logging)

    // Coil
    implementation(libs.coil.compose)

    // Unit Tests
    testImplementation(libs.junit)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockito.core)
    testImplementation(libs.arch.core.testing)
    testImplementation(libs.coroutines.test)

    // Android Tests
    androidTestImplementation(libs.okhttp.idling.resource)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.barista) {
        exclude(group = "org.jetbrains.kotlin")
    }
}
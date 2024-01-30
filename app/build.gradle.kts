plugins {
    id("com.android.application")
    id("androidx.navigation.safeargs")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")

}

android {
    namespace = "com.delivero.customer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.delivero.customer"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding=true
        buildConfig=true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        create("canary"){
            initWith(getByName("debug"))
            applicationIdSuffix=".dev"
        }
    }

    flavorDimensions += "version"
    productFlavors {
        create("dev"){
            dimension="version"
            versionNameSuffix="-canary1"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
    implementation ("androidx.core:core-splashscreen:1.0.1")

    implementation(platform("com.google.firebase:firebase-bom:32.7.1"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-database")
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-storage")
    implementation("com.google.firebase:firebase-inappmessaging-display")
    implementation("com.google.firebase:firebase-messaging")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-config-ktx")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation ("com.firebase:geofire-android:3.2.0")
    implementation ("com.google.android.libraries.places:places:3.3.0")
    implementation("com.google.maps:google-maps-services:2.2.0")


    implementation ("org.greenrobot:eventbus:3.3.1")
    implementation ("com.github.jianastrero:capiche:1.0")
    implementation("io.coil-kt:coil:2.5.0")
    implementation("com.amitshekhar.android:android-networking:1.0.2")
    implementation("com.google.firebase:firebase-config-ktx:21.6.0")
    implementation("com.github.serhatleventyavas:RippleMapView:1.0.0")
    implementation ("com.github.nguyenhoanglam:ImagePicker:1.6.3")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.7.20"
    id("com.android.library")
    id("com.squareup.sqldelight")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {

//                SQLDelight
                implementation("com.squareup.sqldelight:runtime:1.5.5")
                //Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")
                //Logger
                implementation ("io.github.aakira:napier:2.6.1")
                //JSON
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
                //Key-Value storage
                //implementation("com.russhwolf:multiplatform-settings-no-arg:1.0.0")
                // DI
                implementation("io.insert-koin:koin-core:3.3.2")
                //DateTime
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting{
            dependencies{
                implementation("com.squareup.sqldelight:android-driver:1.5.5")
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies{
                implementation("com.squareup.sqldelight:native-driver:1.5.5")
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

sqldelight {
    database("CoworkingDatabase") {
        packageName = "com.issart.coworking.database"
        sourceFolders = listOf("sqldelight")
    }
}

android {
    namespace = "com.issart.coworking"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}
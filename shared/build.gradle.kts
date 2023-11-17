@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.sqlDelight)
    alias(libs.plugins.apollographql)
    kotlin("plugin.serialization").version("1.9.20")
}

kotlin {
    androidTarget {
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
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.sqlDelight.android.driver)
            implementation(libs.androidx.lifecycle.viewmodel.compose)
            implementation(libs.ktor.client.android)
        }
        iosMain.dependencies {
            implementation(libs.sqlDelight.native.driver)
            implementation(libs.ktor.client.darwin)
        }
        commonMain.dependencies {
            implementation(libs.sqlDelight.coroutines.extensions)
            implementation(libs.koin.core)
            implementation(libs.apollo.runtime.kotlin)

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.logging)

            api(libs.logging)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.yveskalume.herofeeds"
    compileSdk = 34
    defaultConfig {
        minSdk = 33
    }
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("com.yveskalume.herofeeds.data.local")
        }
    }
}

apollo {
    generateKotlinModels.set(true)
    packageNamesFromFilePaths()
}

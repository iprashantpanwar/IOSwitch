import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.vanniktechPublish)
    alias(libs.plugins.binaryCompatibilityValidator)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.dokka)
}

group = property("GROUP") as String
version = property("VERSION_NAME") as String

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "IOSwitch"
            isStatic = true
        }
    }
    
    jvm()
    
    js {
        browser()
    }
    
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }
    
    android {
       namespace = "io.iprashantpanwar.ioswitch"
       compileSdk = libs.versions.android.compileSdk.get().toInt()
       minSdk = libs.versions.android.minSdk.get().toInt()
    
       compilerOptions {
           jvmTarget = JvmTarget.JVM_11
       }
       androidResources {
           enable = true
       }
       withHostTest {
           isIncludeAndroidResources = true
       }
       withDeviceTestBuilder {
           sourceSetTreeName = "test"
       }.configure {
           instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
       }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.core.ktx)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.compose.uiTooling)
        }
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jsMain.dependencies {
            implementation(libs.wrappers.browser)
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)
}

mavenPublishing {


    publishToMavenCentral()

    signAllPublications()

    pom {
        name.set("IOSwitch")
        description.set("A beautifully animated switch for Compose Multiplatform.")
        url.set("https://github.com/iprashantpanwar/IOSwitch")

        licenses {
            license {
                name.set("Apache License 2.0")
                url.set(
                    "https://www.apache.org/licenses/LICENSE-2.0.txt"
                )
            }
        }

        developers {
            developer {
                id.set("iprashantpanwar")
                name.set("Prashant Panwar")
                url.set("https://github.com/iprashantpanwar")
            }
        }

        scm {
            connection.set("scm:git:git://github.com/iprashantpanwar/IOSwitch.git")
            developerConnection.set("scm:git:ssh://github.com/iprashantpanwar/IOSwitch.git")
            url.set("https://github.com/iprashantpanwar/IOSwitch")
        }
    }
}

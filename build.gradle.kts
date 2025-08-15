val containerImagePostgres = project.properties["containerImage.postgres"] as String

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.buildconfig)
    application
}

group = "io.github.momosetkn"
version = "1.0-SNAPSHOT"

// check ksp version
run {
    val kotlinVersion = libs.versions.kotlin.get()
    val kspVersion = libs.versions.ksp.get()
    val kotlinPartInKsp = kspVersion.split("-")[0]

    check(kotlinPartInKsp == kotlinVersion) {
        "KSP version mismatch: expected $kotlinVersion-<suffix>, but was $kspVersion"
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.kotlin.test)
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

buildConfig {
    packageName("com.example.build")
    // "postgres:15.8" を定数化
    buildConfigField(
        "String",
        "POSTGRES_IMAGE",
        "\"$containerImagePostgres\""
    )
}

application {
    mainClass.set("io.github.momosetkn.MainKt")
}

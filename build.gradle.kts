val containerImagePostgres = project.properties["containerImage.postgres"] as String

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.buildconfig)
    application
}

group = "io.github.momosetkn"
version = "1.0-SNAPSHOT"

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

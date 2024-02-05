import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

plugins {
    kotlin("jvm") version "1.9.22"
    application
}

group = "org.example"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("io.javalin:javalin:6.0.0")
    implementation("org.slf4j:slf4j-simple:2.0.10")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

tasks.withType<KotlinCompile>().all {
    kotlinOptions {
        jvmTarget = "11"
    }
}

application {
    mainClass.set("MainKt")
}
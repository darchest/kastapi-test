val ktorVersion = "2.3.12"

plugins {
    kotlin("jvm") version "2.0.10"
    id("com.google.devtools.ksp") version "2.0.10-1.0.24"
    application
}

group = "org.darchest"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    maven(url = "https://mvn.darchest.org/repository/snapshots/")
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-content-negotiation-jvm:${ktorVersion}")
    implementation("io.ktor:ktor-server-core-jvm:${ktorVersion}")
    implementation("io.ktor:ktor-serialization-gson-jvm:${ktorVersion}")
    implementation("io.ktor:ktor-server-netty-jvm:${ktorVersion}")

    implementation("org.darchest.kastapi:annotations:1.0-SNAPSHOT")
    implementation("org.darchest.kastapi:ktor-utility:1.0-SNAPSHOT")
    ksp("org.darchest.kastapi:processor:1.0-SNAPSHOT")

    implementation("ch.qos.logback:logback-classic:1.3.14")
    implementation("ch.qos.logback:logback-core:1.3.14")

    implementation("io.ktor:ktor-server-swagger:${ktorVersion}")

    testImplementation(kotlin("test"))
}

ksp {
    arg("org.darchest.kastapi.defaultWrappers", "org.test.EmptyWrapper")
    arg("org.darchest.kastapi.openapi.package.api.path", "api")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}
application {
    mainClass.set("org.test.AppKt")
}

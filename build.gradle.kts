plugins {
    application
}

group = "com.refunddag"
version = "0.1.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.javalin:javalin:6.7.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.22.3")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.22.3")
    runtimeOnly("org.slf4j:slf4j-simple:2.0.20")

    testImplementation(platform("org.junit:junit-bom:5.14.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass = "com.refunddag.App"
}

tasks.test {
    useJUnitPlatform()
}

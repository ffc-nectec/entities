
plugins {
    kotlin("jvm") version "1.2.50"
    id("org.jlleitschuh.gradle.ktlint") version "4.1.0"
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))
    implementation("joda-time:joda-time:2.9.9")
    implementation("com.github.piruin:geok:1.0.0-alpha-2")
}

repositories {
    mavenCentral()
    google()
    jcenter()
    maven("https://jitpack.io")
}

task<Wrapper>("wrapper") {
    gradleVersion = "4.8"
    distributionUrl = distributionUrl.replace("bin", "all")
}

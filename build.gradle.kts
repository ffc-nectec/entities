
plugins {
    kotlin("jvm") version "1.2.50"
    id("org.jlleitschuh.gradle.ktlint") version "4.1.0"
    maven
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))
    implementation("joda-time:joda-time:2.9.9")
    implementation("com.github.piruin:geok:1.0.0-alpha-2")
    implementation("com.google.code.gson:gson:2.8.1")
    implementation("com.fatboyindustrial.gson-jodatime-serialisers:gson-jodatime-serialisers:1.6.0")

    testImplementation("junit:junit:4.12")
    testImplementation("com.gregwoodfill.assert:kotlin-json-assert:0.1.0")
    testImplementation("org.amshove.kluent:kluent:1.34")
    testImplementation("com.github.ffc-nectec:genogram:master")
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

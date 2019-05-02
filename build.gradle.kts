/*
 * Copyright (c) 2019 NECTEC
 *   National Electronics and Computer Technology Center, Thailand
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


plugins {
    kotlin("jvm") version "1.3.0"
    id("org.jlleitschuh.gradle.ktlint") version "4.1.0"
    maven
}

repositories {
    mavenCentral()
    google()
    jcenter()
    maven("https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))
    implementation("joda-time:joda-time:2.9.9")
    implementation("com.github.piruin.geok:geok:1.0.0")
    implementation("com.github.piruin.geok:geok-gson:1.0.0")
    implementation("com.google.code.gson:gson:2.8.1")
    implementation("com.googlecode.libphonenumber:libphonenumber:8.10.11")
    implementation("com.fatboyindustrial.gson-jodatime-serialisers:gson-jodatime-serialisers:1.6.0")

    testImplementation("junit:junit:4.12")
    testImplementation("com.gregwoodfill.assert:kotlin-json-assert:0.1.0")
    testImplementation("org.amshove.kluent:kluent:1.34")
    testImplementation("com.github.ffc-nectec:genogram:master")
}

task<Wrapper>("wrapper") {
    gradleVersion = "4.8"
    distributionUrl = distributionUrl.replace("bin", "all")
}

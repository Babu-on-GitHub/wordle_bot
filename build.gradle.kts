plugins {
    id("java")
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation("net.jqwik:jqwik:1.7.4")
    implementation("org.seleniumhq.selenium:selenium-java:4.10.0")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("main.Main")
}
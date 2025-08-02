plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.25")
    implementation("org.jetbrains.kotlin:kotlin-allopen:1.9.25")
    implementation("org.jetbrains.kotlin:kotlin-noarg:1.9.25")
    implementation("org.springframework.boot:spring-boot-gradle-plugin:3.5.4")
    implementation("io.spring.gradle:dependency-management-plugin:1.1.7")
}

plugins {
    id("kotlin-common-conventions")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    val implementation by configurations
    val testImplementation by configurations

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    archiveClassifier.set("boot")
}

tasks.named<Jar>("jar") {
    archiveClassifier.set("")
    enabled = true
}

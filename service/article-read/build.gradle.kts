plugins {
    id("spring-boot-application-conventions")
}

dependencies {
    implementation(project(":core:common-jpa"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
}
plugins {
    id("kotlin-common-conventions")
}

description = "Article application module - contains use cases, ports, and application services"

dependencies {
    val implementation by configurations

    implementation(project(":service:article:article-domain"))

    // Spring 의존성 추가
    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-tx")

    // 테스트 의존성
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")
    testImplementation("io.mockk:mockk:1.13.8")
    testImplementation("org.instancio:instancio-kotlin:3.3.0")
    testImplementation(testFixtures(project(":service:article:article-domain")))
}

// Kotest 설정
tasks.withType<Test> {
    useJUnitPlatform()
}

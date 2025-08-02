plugins {
    id("kotlin-common-conventions")
    id("java-test-fixtures")
}

description = "Article domain module - contains domain entities, value objects, and domain services"

dependencies {
    // 테스트 의존성
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")
    testImplementation("io.mockk:mockk:1.13.8")
    testImplementation("org.instancio:instancio-junit:5.5.0")

    // TestFixtures 의존성
    testFixturesImplementation("org.instancio:instancio-junit:5.5.0")
}

// Kotest 설정
tasks.withType<Test> {
    useJUnitPlatform()
}

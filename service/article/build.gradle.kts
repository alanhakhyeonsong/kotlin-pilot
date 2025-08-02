plugins {
    id("spring-boot-application-conventions")
}

description = "Article service main application - Spring Boot executable"

dependencies {
    val implementation by configurations

    implementation(project(":service:article:article-domain"))
    implementation(project(":service:article:article-application"))
    implementation(project(":service:article:article-adapter:article-jpa-out-adapter"))
    implementation(project(":service:article:article-adapter:article-web-in-adapter"))
}
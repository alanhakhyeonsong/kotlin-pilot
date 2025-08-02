plugins {
    id("spring-web-adapter-conventions")
}

description = "Article web in adapter - REST API controllers and web configurations"

dependencies {
    val implementation by configurations

    implementation(project(":service:article:article-domain"))
    implementation(project(":service:article:article-application"))
}

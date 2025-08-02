plugins {
    id("spring-data-jpa-conventions")
}

description = "Article JPA out adapter - implements repository ports using JPA"

dependencies {
    val implementation by configurations

    implementation(project(":core:common-jpa"))
    implementation(project(":service:article:article-domain"))
    implementation(project(":service:article:article-application"))
}

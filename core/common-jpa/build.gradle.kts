plugins {
    id("spring-data-jpa-conventions")
}

description = "Common JPA configurations and base entities"

dependencies {
    val api by configurations

    api("org.springframework.boot:spring-boot-starter-data-jpa")
    api("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    api("org.springframework.boot:spring-boot-starter-data-redis")
}

# Kotlin Pilot
Kotlin + Spring Boot Pilot Project

- Hexagonal Architecture with Gradle Multi Module
- Microservice Architecture

```
rootDir
├── core # 각 도메인 별 공통 설정
│   └── common-jpa
│       ├── config
│       └── model
└── service # 서브 모듈
		└── {domain} # 도메인별 모듈 (ex. article, comment, ...)
		    ├── {domain}-domain # 도메인 패키지
		    ├── {domain}-application # 어플리케이션 패키지(port, service)
		    └── {domain}-adapter # 어댑터 패키지
		        ├── {domain}-jpa-out-adapter 
		        └── {domain}-web-in-adapter
		    └── Application.kt # Spring Boot 애플리케이션 실행 모듈
```

## Tech Stack
- Kotlin 1.9.25 (Java 21)
- Spring Boot 3.5.4
- Spring Web MVC, Spring Data JPA + QueryDSL
- Spring Cloud OpenFeign, Spring Cloud CircuitBreaker (Resilience4J)
- Kotest, Mockk, Instancio, ArchUnit
- MySQL / H2 Database
- OpenAPI 3 (Swagger)
- Kotlin Logging (Logback)
- Redis (Spring Data Redis)
- Kafka (Spring for Apache Kafka)

## More Info
- https://docs.gradle.org/current/userguide/implementing_gradle_plugins_precompiled.html
- https://docs.gradle.org/current/samples/sample_convention_plugins.html
- https://docs.gradle.org/current/userguide/platforms.html
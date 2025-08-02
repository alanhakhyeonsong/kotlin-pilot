plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "kotlin-pilot"

include(
    "core",
    "core:common-jpa",
    "service",
    "service:article",
    "service:article:article-domain",
    "service:article:article-application",
    "service:article:article-adapter",
    "service:article:article-adapter:article-jpa-out-adapter",
    "service:article:article-adapter:article-web-in-adapter",
    "service:article-read",
    "service:comment",
    "service:hot-article",
    "service:like",
    "service:view"
)

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "kotlin-pilot"

include(
    "core",
    "service",
    "service:article",
    "service:article-read",
    "service:comment",
    "service:hot-article",
    "service:like",
    "service:view"
)

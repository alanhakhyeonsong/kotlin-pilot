package me.ramos.article.bootstrap

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

/**
 * 게시글 서비스 메인 애플리케이션
 * Spring Boot 애플리케이션의 진입점이며, 헥사고날 아키텍처 모듈들을 조합합니다.
 *
 * @author HakHyeon Song
 */
@SpringBootApplication(
    scanBasePackages = [
        "me.ramos.article.application",
        "me.ramos.article.adapter",
        "me.ramos.core.jpa"
    ]
)
@EnableJpaRepositories(
    basePackages = [
        "me.ramos.article.adapter.out.persistence.repository"
    ]
)
@EntityScan(
    basePackages = [
        "me.ramos.article.adapter.out.persistence.entity"
    ]
)
class ArticleApplication

fun main(args: Array<String>) {
    runApplication<ArticleApplication>(*args)
}

package me.ramos.article.adapter.out.persistence

import me.ramos.article.adapter.out.persistence.entity.ArticleJpaEntity
import me.ramos.article.adapter.out.persistence.repository.ArticleJpaRepository
import me.ramos.article.application.port.out.ArticleCommandPort
import me.ramos.article.domain.model.Article
import org.springframework.stereotype.Component

/**
 * 게시글 영속성 Command 어댑터
 * 게시글 도메인과 JPA 데이터베이스 간의 변환 및 영속성을 담당합니다.
 *
 * @author HakHyeon Song
 */
@Component
class ArticleCommandPersistenceAdapter(
    private val articleJpaRepository: ArticleJpaRepository
) : ArticleCommandPort {

    override fun saveArticle(article: Article): Article {
        return articleJpaRepository.save(ArticleJpaEntity.fromDomain(article))
            .toDomain()
    }

    override fun deleteArticle(id: Long) {
        articleJpaRepository.deleteById(id)
    }
}

package me.ramos.article.adapter.out.persistence

import me.ramos.article.adapter.out.persistence.entity.ArticleJpaEntity
import me.ramos.article.adapter.out.persistence.repository.ArticleJpaRepository
import me.ramos.article.application.port.out.ArticleQueryPort
import me.ramos.article.domain.model.Article
import org.springframework.stereotype.Component

/**
 * 게시글 영속성 Query 어댑터
 * 게시글 도메인과 JPA 데이터베이스 간의 변환 및 영속성을 담당합니다.
 *
 * @author HakHyeon Song
 */
@Component
class ArticleQueryPersistenceAdapter(
    private val articleJpaRepository: ArticleJpaRepository
) : ArticleQueryPort {

    override fun loadArticle(id: Long): Article? {
        return articleJpaRepository.findById(id)
            .orElse(null)
            ?.toDomain()
    }

    override fun loadAllArticles(boardId: Long, pageSize: Long, lastArticleId: Long?): List<Article> {
        return (lastArticleId
            ?.let { articleJpaRepository.findAllInfiniteScroll(boardId, pageSize, it) }
            ?: articleJpaRepository.findAllInfiniteScroll(boardId, pageSize)
                )
            .map(ArticleJpaEntity::toDomain)
    }

    override fun loadArticlesByBoard(boardId: Long): List<Article> {
        return articleJpaRepository.findByBoardIdOrderByArticleIdDesc(boardId)
            .map { it.toDomain() }
    }

    override fun loadArticlesByWriter(writerId: Long): List<Article> {
        return articleJpaRepository.findByWriterIdOrderByArticleIdDesc(writerId)
            .map { it.toDomain() }
    }

    override fun existsArticle(id: Long): Boolean {
        return articleJpaRepository.existsById(id)
    }
}

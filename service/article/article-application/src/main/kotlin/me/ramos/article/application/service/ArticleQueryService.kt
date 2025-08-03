package me.ramos.article.application.service

import me.ramos.article.application.port.`in`.ArticleQueryUseCase
import me.ramos.article.application.port.out.ArticleQueryPort
import me.ramos.article.domain.model.Article
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 게시글 조회 서비스 구현체
 * 게시글 조회 관련 비즈니스 로직을 처리합니다.
 *
 * @author HakHyeon Song
 */
@Service
@Transactional(readOnly = true)
class ArticleQueryService(
    private val articleQueryPort: ArticleQueryPort
) : ArticleQueryUseCase {

    override fun getArticle(id: Long): Article? {
        return articleQueryPort.loadArticle(id)
    }

    override fun getAllArticles(boardId: Long, pageSize: Long, lastArticleId: Long?): List<Article> {
        return articleQueryPort.loadAllArticles(boardId, pageSize, lastArticleId)
    }

    override fun getArticlesByBoard(boardId: Long): List<Article> {
        return articleQueryPort.loadArticlesByBoard(boardId)
    }

    override fun getArticlesByWriter(writerId: Long): List<Article> {
        return articleQueryPort.loadArticlesByWriter(writerId)
    }
}

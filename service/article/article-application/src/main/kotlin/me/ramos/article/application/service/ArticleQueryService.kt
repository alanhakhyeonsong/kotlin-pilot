package me.ramos.article.application.service

import me.ramos.article.application.port.`in`.ArticleQueryUseCase
import me.ramos.article.application.port.out.ArticlePort
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
    private val articlePort: ArticlePort
) : ArticleQueryUseCase {

    override fun getArticle(id: Long): Article? {
        return articlePort.loadArticle(id)
    }

    override fun getAllArticles(): List<Article> {
        return articlePort.loadAllArticles()
    }

    override fun getArticlesByBoard(boardId: Long): List<Article> {
        return articlePort.loadArticlesByBoard(boardId)
    }

    override fun getArticlesByWriter(writerId: Long): List<Article> {
        return articlePort.loadArticlesByWriter(writerId)
    }
}

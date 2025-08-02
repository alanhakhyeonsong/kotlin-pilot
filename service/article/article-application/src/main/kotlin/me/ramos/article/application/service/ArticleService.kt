package me.ramos.article.application.service

import me.ramos.article.application.port.`in`.ArticleUseCase
import me.ramos.article.application.port.`in`.CreateArticleCommand
import me.ramos.article.application.port.`in`.UpdateArticleCommand
import me.ramos.article.domain.model.Article
import org.springframework.stereotype.Service

/**
 * 게시글 통합 서비스 구현체
 * Query와 Command 서비스를 조합하여 통합된 인터페이스를 제공합니다.
 *
 * @author HakHyeon Song
 */
@Service
class ArticleService(
    private val articleQueryService: ArticleQueryService,
    private val articleCommandService: ArticleCommandService
) : ArticleUseCase {

    // Query 위임
    override fun getArticle(id: Long): Article? = articleQueryService.getArticle(id)
    override fun getAllArticles(): List<Article> = articleQueryService.getAllArticles()
    override fun getArticlesByBoard(boardId: Long): List<Article> = articleQueryService.getArticlesByBoard(boardId)
    override fun getArticlesByWriter(writerId: Long): List<Article> = articleQueryService.getArticlesByWriter(writerId)

    // Command 위임
    override fun createArticle(command: CreateArticleCommand): Article = articleCommandService.createArticle(command)
    override fun updateArticle(command: UpdateArticleCommand): Article = articleCommandService.updateArticle(command)
    override fun deleteArticle(id: Long) = articleCommandService.deleteArticle(id)
    override fun increaseViewCount(id: Long): Article = articleCommandService.increaseViewCount(id)
}

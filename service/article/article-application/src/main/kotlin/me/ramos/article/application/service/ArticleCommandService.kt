package me.ramos.article.application.service

import me.ramos.article.application.port.`in`.ArticleCommandUseCase
import me.ramos.article.application.port.`in`.CreateArticleCommand
import me.ramos.article.application.port.`in`.UpdateArticleCommand
import me.ramos.article.application.port.out.ArticlePort
import me.ramos.article.domain.model.Article
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 게시글 명령 서비스 구현체
 * 게시글 생성, 수정, 삭제 관련 비즈니스 로직을 처리합니다.
 *
 * @author HakHyeon Song
 */
@Service
@Transactional
class ArticleCommandService(
    private val articlePort: ArticlePort
) : ArticleCommandUseCase {

    override fun createArticle(command: CreateArticleCommand): Article {
        val article = Article(
            title = command.title,
            content = command.content,
            boardId = command.boardId,
            writerId = command.writerId
        )
        return articlePort.saveArticle(article)
    }

    override fun updateArticle(command: UpdateArticleCommand): Article {
        val existingArticle = articlePort.loadArticle(command.id)
            ?: throw IllegalArgumentException("Article not found with id: ${command.id}")

        val updatedArticle = existingArticle.update(command.title, command.content)
        return articlePort.saveArticle(updatedArticle)
    }

    override fun deleteArticle(id: Long) {
        if (!articlePort.existsArticle(id)) {
            throw IllegalArgumentException("Article not found with id: $id")
        }
        articlePort.deleteArticle(id)
    }

    override fun increaseViewCount(id: Long): Article {
        TODO("Not yet implemented")
    }
}

package me.ramos.article.adapter.`in`.web

import jakarta.validation.Valid
import me.ramos.article.application.port.`in`.ArticleUseCase
import me.ramos.article.application.port.`in`.CreateArticleCommand
import me.ramos.article.application.port.`in`.UpdateArticleCommand
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * 게시글 웹 컨트롤러
 * 게시글 관련 REST API 엔드포인트를 제공합니다.
 *
 * @author HakHyeon Song
 */
@RestController
@RequestMapping("/api/articles")
class ArticleController(
    private val articleUseCase: ArticleUseCase
) {

    @PostMapping
    fun createArticle(@Valid @RequestBody request: CreateArticleRequest): ResponseEntity<ArticleResponse> {
        val command = CreateArticleCommand(
            title = request.title,
            content = request.content,
            boardId = request.boardId,
            writerId = request.writerId
        )
        val article = articleUseCase.createArticle(command)
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ArticleResponse.from(article))
    }

    @GetMapping("/{id}")
    fun getArticle(@PathVariable id: Long): ResponseEntity<ArticleResponse> {
        val article = articleUseCase.getArticle(id)
            ?: return ResponseEntity.notFound().build()

        // 조회시 view count 증가
        val updatedArticle = articleUseCase.increaseViewCount(id)
        return ResponseEntity.ok(ArticleResponse.from(updatedArticle))
    }

    @GetMapping
    fun getAllArticles(): ResponseEntity<List<ArticleResponse>> {
        val articles = articleUseCase.getAllArticles()
        return ResponseEntity.ok(articles.map { ArticleResponse.from(it) })
    }

    @GetMapping("/board/{boardId}")
    fun getArticlesByBoard(@PathVariable boardId: Long): ResponseEntity<List<ArticleResponse>> {
        val articles = articleUseCase.getArticlesByBoard(boardId)
        return ResponseEntity.ok(articles.map { ArticleResponse.from(it) })
    }

    @GetMapping("/writer/{writerId}")
    fun getArticlesByWriter(@PathVariable writerId: Long): ResponseEntity<List<ArticleResponse>> {
        val articles = articleUseCase.getArticlesByWriter(writerId)
        return ResponseEntity.ok(articles.map { ArticleResponse.from(it) })
    }

    @PutMapping("/{id}")
    fun updateArticle(
        @PathVariable id: Long,
        @Valid @RequestBody request: UpdateArticleRequest
    ): ResponseEntity<ArticleResponse> {
        val command = UpdateArticleCommand(
            id = id,
            title = request.title,
            content = request.content
        )
        val article = articleUseCase.updateArticle(command)
        return ResponseEntity.ok(ArticleResponse.from(article))
    }

    @DeleteMapping("/{id}")
    fun deleteArticle(@PathVariable id: Long): ResponseEntity<Void> {
        articleUseCase.deleteArticle(id)
        return ResponseEntity.noContent().build()
    }
}

package me.ramos.article.adapter.`in`.web

import jakarta.validation.Valid
import me.ramos.article.application.port.`in`.ArticleCommandUseCase
import me.ramos.article.application.port.`in`.ArticleQueryUseCase
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
    private val articleQueryUseCase: ArticleQueryUseCase,
    private val articleCommandUseCase: ArticleCommandUseCase,
) {

    @PostMapping
    fun createArticle(@Valid @RequestBody request: CreateArticleRequest): ResponseEntity<ArticleResponse> {
        val command = CreateArticleCommand(
            title = request.title,
            content = request.content,
            boardId = request.boardId,
            writerId = request.writerId
        )
        val article = articleCommandUseCase.createArticle(command)
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ArticleResponse.from(article))
    }

    @GetMapping("/{id}")
    fun getArticle(@PathVariable id: Long): ResponseEntity<ArticleResponse> {
        val article = articleQueryUseCase.getArticle(id)
            ?: return ResponseEntity.notFound().build()

        // TODO : 조회시 view count 증가
        return ResponseEntity.ok(ArticleResponse.from(article))
    }

    @GetMapping
    fun getAllArticles(): ResponseEntity<List<ArticleResponse>> {
        val articles = articleQueryUseCase.getAllArticles()
        return ResponseEntity.ok(articles.map { ArticleResponse.from(it) })
    }

    @GetMapping("/board/{boardId}")
    fun getArticlesByBoard(@PathVariable boardId: Long): ResponseEntity<List<ArticleResponse>> {
        val articles = articleQueryUseCase.getArticlesByBoard(boardId)
        return ResponseEntity.ok(articles.map { ArticleResponse.from(it) })
    }

    @GetMapping("/writer/{writerId}")
    fun getArticlesByWriter(@PathVariable writerId: Long): ResponseEntity<List<ArticleResponse>> {
        val articles = articleQueryUseCase.getArticlesByWriter(writerId)
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
        val article = articleCommandUseCase.updateArticle(command)
        return ResponseEntity.ok(ArticleResponse.from(article))
    }

    @DeleteMapping("/{id}")
    fun deleteArticle(@PathVariable id: Long): ResponseEntity<Void> {
        articleCommandUseCase.deleteArticle(id)
        return ResponseEntity.noContent().build()
    }
}

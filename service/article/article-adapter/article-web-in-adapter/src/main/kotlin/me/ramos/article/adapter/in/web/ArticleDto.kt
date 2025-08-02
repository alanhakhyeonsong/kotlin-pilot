package me.ramos.article.adapter.`in`.web

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import me.ramos.article.domain.model.Article
import java.time.LocalDateTime

/**
 * 게시글 생성 요청 DTO
 * 게시글 생성 시 클라이언트로부터 받는 데이터를 정의합니다.
 *
 * @author HakHyeon Song
 */
data class CreateArticleRequest(
    @field:NotBlank(message = "Title is required")
    @field:Size(max = 100, message = "Title must be less than 100 characters")
    val title: String,

    @field:NotBlank(message = "Content is required")
    @field:Size(max = 3000, message = "Content must be less than 3000 characters")
    val content: String,

    @field:NotNull(message = "Board ID is required")
    @field:Positive(message = "Board ID must be positive")
    val boardId: Long,

    @field:NotNull(message = "Writer ID is required")
    @field:Positive(message = "Writer ID must be positive")
    val writerId: Long
)

/**
 * 게시글 수정 요청 DTO
 * 게시글 수정 시 클라이언트로부터 받는 데이터를 정의합니다.
 *
 * @author HakHyeon Song
 */
data class UpdateArticleRequest(
    @field:NotBlank(message = "Title is required")
    @field:Size(max = 100, message = "Title must be less than 100 characters")
    val title: String,

    @field:NotBlank(message = "Content is required")
    @field:Size(max = 3000, message = "Content must be less than 3000 characters")
    val content: String
)

/**
 * 게시글 응답 DTO
 * 게시글 정보를 클라이언트에게 반환하는 데이터를 정의합니다.
 *
 * @author HakHyeon Song
 */
data class ArticleResponse(
    val id: Long,
    val title: String,
    val content: String,
    val boardId: Long,
    val writerId: Long,
    val viewCount: Long,
    val likeCount: Long,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
) {
    companion object {
        fun from(article: Article): ArticleResponse {
            return ArticleResponse(
                id = article.id!!,
                title = article.title,
                content = article.content,
                boardId = article.boardId,
                writerId = article.writerId,
                viewCount = article.viewCount,
                likeCount = article.likeCount,
                createdAt = article.createdAt,
                updatedAt = article.updatedAt
            )
        }
    }
}

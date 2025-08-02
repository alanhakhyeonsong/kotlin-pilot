package me.ramos.article.application.port.`in`

import me.ramos.article.domain.model.Article

/**
 * 게시글 유스케이스 인터페이스 (통합)
 * Query와 Command UseCase를 통합한 편의성 인터페이스입니다.
 *
 * @author HakHyeon Song
 */
interface ArticleUseCase : ArticleQueryUseCase, ArticleCommandUseCase

/**
 * 게시글 Query UseCase 인터페이스
 * 게시글 조회 관련 비즈니스 작업의 진입점을 정의합니다.
 *
 * @author HakHyeon Song
 */
interface ArticleQueryUseCase {
    fun getArticle(id: Long): Article?
    fun getAllArticles(): List<Article>
    fun getArticlesByBoard(boardId: Long): List<Article>
    fun getArticlesByWriter(writerId: Long): List<Article>
}

/**
 * 게시글 Command UseCase 인터페이스
 * 게시글 생성, 수정, 삭제 관련 비즈니스 작업의 진입점을 정의합니다.
 *
 * @author HakHyeon Song
 */
interface ArticleCommandUseCase {
    fun createArticle(command: CreateArticleCommand): Article
    fun updateArticle(command: UpdateArticleCommand): Article
    fun deleteArticle(id: Long)
    fun increaseViewCount(id: Long): Article
}

/**
 * 게시글 생성 명령
 *
 * @author HakHyeon Song
 */
data class CreateArticleCommand(
    val title: String,
    val content: String,
    val boardId: Long,
    val writerId: Long
)

/**
 * 게시글 수정 명령
 *
 * @author HakHyeon Song
 */
data class UpdateArticleCommand(
    val id: Long,
    val title: String,
    val content: String
)

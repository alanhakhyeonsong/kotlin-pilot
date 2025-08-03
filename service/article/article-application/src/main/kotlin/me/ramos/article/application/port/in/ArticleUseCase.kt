package me.ramos.article.application.port.`in`

import me.ramos.article.domain.model.Article


/**
 * 게시글 Query UseCase 인터페이스
 * 게시글 조회 관련 비즈니스 작업의 진입점을 정의합니다.
 *
 * @author HakHyeon Song
 */
interface ArticleQueryUseCase {

    /**
     * 게시글 ID로 게시글을 조회합니다.
     *
     * @param id 게시글 ID
     * @return 조회된 게시글, 없으면 null
     */
    fun getArticle(id: Long): Article?

    /**
     * 게시판 ID와 페이지 크기를 기준으로 게시글을 목록 조회합니다.
     *
     * @param boardId 게시판 ID
     * @param pageSize 페이지 크기
     * @param lastArticleId 마지막으로 조회된 게시글 ID (무한 스크롤 용)
     * @return 조회된 게시글 목록
     */
    fun getAllArticles(boardId: Long, pageSize: Long, lastArticleId: Long?): List<Article>

    /**
     * 게시판 ID로 게시글 목록을 조회합니다.
     *
     * @param boardId 게시판 ID
     * @return 해당 게시판의 게시글 목록
     */
    fun getArticlesByBoard(boardId: Long): List<Article>

    /**
     * 작성자 ID로 게시글 목록을 조회합니다.
     *
     * @param writerId 작성자 ID
     * @return 해당 작성자의 게시글 목록
     */
    fun getArticlesByWriter(writerId: Long): List<Article>
}

/**
 * 게시글 Command UseCase 인터페이스
 * 게시글 생성, 수정, 삭제 관련 비즈니스 작업의 진입점을 정의합니다.
 *
 * @author HakHyeon Song
 */
interface ArticleCommandUseCase {

    /**
     * 게시글을 생성합니다.
     *
     * @param command 게시글 생성 명령
     * @return 생성된 게시글
     */
    fun createArticle(command: CreateArticleCommand): Article

    /**
     * 게시글을 수정합니다.
     *
     * @param command 게시글 수정 명령
     * @return 수정된 게시글
     */
    fun updateArticle(command: UpdateArticleCommand): Article

    /**
     * 게시글을 삭제합니다.
     *
     * @param id 삭제할 게시글 ID
     */
    fun deleteArticle(id: Long)
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

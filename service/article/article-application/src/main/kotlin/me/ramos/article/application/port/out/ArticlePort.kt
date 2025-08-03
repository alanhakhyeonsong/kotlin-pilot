package me.ramos.article.application.port.out

import me.ramos.article.domain.model.Article

/**
 * 게시글 아웃바운드 Query 포트
 *
 * @author HakHyeon Song
 */
interface ArticleQueryPort {

    /**
     * 게시글 ID로 게시글을 조회합니다.
     *
     * @param id 게시글 ID
     * @return 조회된 게시글, 없으면 null
     */
    fun loadArticle(id: Long): Article?

    /**
     * 게시판 ID와 페이지 크기를 기준으로 게시글을 조회합니다.
     *
     * @param boardId 게시판 ID
     * @param pageSize 페이지 크기
     * @param lastArticleId 마지막으로 조회된 게시글 ID (무한 스크롤 용)
     * @return 조회된 게시글 목록
     */
    fun loadAllArticles(boardId: Long, pageSize: Long, lastArticleId: Long?): List<Article>

    /**
     * 게시판 ID로 게시글 목록을 조회합니다.
     *
     * @param boardId 게시판 ID
     * @return 해당 게시판의 게시글 목록
     */
    fun loadArticlesByBoard(boardId: Long): List<Article>

    /**
     * 작성자 ID로 게시글 목록을 조회합니다.
     *
     * @param writerId 작성자 ID
     * @return 해당 작성자의 게시글 목록
     */
    fun loadArticlesByWriter(writerId: Long): List<Article>

    /**
     * 게시글이 존재하는지 확인합니다.
     *
     * @param id 게시글 ID
     * @return 존재하면 true, 아니면 false
     */
    fun existsArticle(id: Long): Boolean
}

/**
 * 게시글 아웃바운드 Command 포트
 *
 * @author HakHyeon Song
 */
interface ArticleCommandPort {

    /**
     * 게시글을 저장합니다. 새 게시글 생성 또는 기존 게시글 수정에 사용됩니다.
     *
     * @param article 저장할 게시글
     * @return 저장된 게시글
     */
    fun saveArticle(article: Article): Article

    /**
     * 게시글을 삭제합니다.
     *
     * @param id 삭제할 게시글 ID
     */
    fun deleteArticle(id: Long)
}

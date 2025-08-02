package me.ramos.article.application.port.out

import me.ramos.article.domain.model.Article

/**
 * 게시글 아웃바운드 포트 (통합)
 * 게시글 데이터의 CRUD 기능을 모두 포함하는 통합 포트입니다.
 *
 * @author HakHyeon Song
 */
interface ArticlePort {
    // Query operations
    fun loadArticle(id: Long): Article?
    fun loadAllArticles(): List<Article>
    fun loadArticlesByBoard(boardId: Long): List<Article>
    fun loadArticlesByWriter(writerId: Long): List<Article>
    fun existsArticle(id: Long): Boolean

    // Command operations
    fun saveArticle(article: Article): Article
    fun deleteArticle(id: Long)
}

package me.ramos.article.application.port.out

import me.ramos.article.domain.model.Article

/**
 * 게시글 아웃바운드 Query 포트
 *
 * @author HakHyeon Song
 */
interface ArticleQueryPort {
    fun loadArticle(id: Long): Article?
    fun loadAllArticles(): List<Article>
    fun loadArticlesByBoard(boardId: Long): List<Article>
    fun loadArticlesByWriter(writerId: Long): List<Article>
    fun existsArticle(id: Long): Boolean
}

/**
 * 게시글 아웃바운드 Command 포트
 *
 * @author HakHyeon Song
 */
interface ArticleCommandPort {
    fun saveArticle(article: Article): Article
    fun deleteArticle(id: Long)
}

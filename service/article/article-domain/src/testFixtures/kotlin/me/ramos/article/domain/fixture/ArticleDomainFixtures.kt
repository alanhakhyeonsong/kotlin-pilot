package me.ramos.article.domain.fixture

import me.ramos.article.domain.model.Article
import org.instancio.Instancio
import org.instancio.kotlin.field
import java.time.LocalDateTime

/**
 * Article 도메인 테스트를 위한 Fixtures 클래스
 *
 * @author HakHyeon Song
 */
object ArticleDomainFixtures {

    fun getArticle(): Article {
        return Instancio.of(Article::class.java)
            .set(field(Article::id), 1L)
            .set(field(Article::title), "테스트 게시글 제목")
            .set(field(Article::content), "테스트 게시글 내용")
            .set(field(Article::boardId), 1L)
            .set(field(Article::writerId), 1L)
            .set(field(Article::createdAt), LocalDateTime.now())
            .set(field(Article::modifiedAt), LocalDateTime.now())
            .create()
    }

    fun getArticleWithId(id: Long): Article {
        return Instancio.of(Article::class.java)
            .set(field(Article::id), id)
            .set(field(Article::title), "테스트 게시글 제목")
            .set(field(Article::content), "테스트 게시글 내용")
            .set(field(Article::boardId), 1L)
            .set(field(Article::writerId), 1L)
            .create()
    }

    fun getArticleWithBoardId(boardId: Long): Article {
        return Instancio.of(Article::class.java)
            .set(field(Article::id), 1L)
            .set(field(Article::boardId), boardId)
            .set(field(Article::writerId), 1L)
            .create()
    }

    fun getArticleWithWriterId(writerId: Long): Article {
        return Instancio.of(Article::class.java)
            .set(field(Article::id), 1L)
            .set(field(Article::boardId), 1L)
            .set(field(Article::writerId), writerId)
            .create()
    }

    fun getArticleWithTitleAndContent(title: String, content: String): Article {
        return Instancio.of(Article::class.java)
            .set(field(Article::id), 1L)
            .set(field(Article::title), title)
            .set(field(Article::content), content)
            .set(field(Article::boardId), 1L)
            .set(field(Article::writerId), 1L)
            .create()
    }
}

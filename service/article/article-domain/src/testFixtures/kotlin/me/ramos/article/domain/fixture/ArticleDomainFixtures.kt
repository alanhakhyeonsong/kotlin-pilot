package me.ramos.article.domain.fixture

import me.ramos.article.domain.model.Article
import org.instancio.Instancio
import org.instancio.Select
import java.time.LocalDateTime

/**
 * Article 도메인 테스트를 위한 Fixtures 클래스
 *
 * @author HakHyeon Song
 */
object ArticleDomainFixtures {

    fun getArticle(): Article {
        return Instancio.of(Article::class.java)
            .set(Select.field(Article::id), 1L)
            .set(Select.field(Article::title), "테스트 게시글 제목")
            .set(Select.field(Article::content), "테스트 게시글 내용")
            .set(Select.field(Article::boardId), 1L)
            .set(Select.field(Article::writerId), 1L)
            .set(Select.field(Article::createdAt), LocalDateTime.now())
            .set(Select.field(Article::modifiedAt), LocalDateTime.now())
            .create()
    }

    fun getArticleWithId(id: Long): Article {
        return Instancio.of(Article::class.java)
            .set(Select.field(Article::id), id)
            .set(Select.field(Article::title), "테스트 게시글 제목")
            .set(Select.field(Article::content), "테스트 게시글 내용")
            .set(Select.field(Article::boardId), 1L)
            .set(Select.field(Article::writerId), 1L)
            .create()
    }

    fun getArticleWithBoardId(boardId: Long): Article {
        return Instancio.of(Article::class.java)
            .set(Select.field(Article::id), 1L)
            .set(Select.field(Article::boardId), boardId)
            .set(Select.field(Article::writerId), 1L)
            .create()
    }

    fun getArticleWithWriterId(writerId: Long): Article {
        return Instancio.of(Article::class.java)
            .set(Select.field(Article::id), 1L)
            .set(Select.field(Article::boardId), 1L)
            .set(Select.field(Article::writerId), writerId)
            .create()
    }

    fun getArticleWithTitleAndContent(title: String, content: String): Article {
        return Instancio.of(Article::class.java)
            .set(Select.field(Article::id), 1L)
            .set(Select.field(Article::title), title)
            .set(Select.field(Article::content), content)
            .set(Select.field(Article::boardId), 1L)
            .set(Select.field(Article::writerId), 1L)
            .create()
    }

    fun getArticleWithViewCount(viewCount: Long): Article {
        return Instancio.of(Article::class.java)
            .set(Select.field(Article::id), 1L)
            .set(Select.field(Article::title), "테스트 게시글 제목")
            .set(Select.field(Article::content), "테스트 게시글 내용")
            .set(Select.field(Article::boardId), 1L)
            .set(Select.field(Article::writerId), 1L)
            .create()
    }
}

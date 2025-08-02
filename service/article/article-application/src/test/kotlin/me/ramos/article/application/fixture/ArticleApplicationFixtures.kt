package me.ramos.article.application.fixture

import me.ramos.article.application.port.`in`.CreateArticleCommand
import me.ramos.article.application.port.`in`.UpdateArticleCommand
import me.ramos.article.domain.fixture.ArticleDomainFixtures
import me.ramos.article.domain.fixture.KSelect
import me.ramos.article.domain.model.Article
import org.instancio.Instancio

/**
 * Article Application 테스트를 위한 Fixtures 클래스
 *
 * @author HakHyeon Song
 */
object ArticleApplicationFixtures {

    fun getCreateArticleCommand(): CreateArticleCommand {
        return Instancio.of(CreateArticleCommand::class.java)
            .set(KSelect.field(CreateArticleCommand::title), "테스트 게시글 제목")
            .set(KSelect.field(CreateArticleCommand::content), "테스트 게시글 내용")
            .set(KSelect.field(CreateArticleCommand::boardId), 1L)
            .set(KSelect.field(CreateArticleCommand::writerId), 1L)
            .create()
    }

    fun getCreateArticleCommandWithBoardIdAndWriterId(boardId: Long, writerId: Long): CreateArticleCommand {
        return Instancio.of(CreateArticleCommand::class.java)
            .set(KSelect.field(CreateArticleCommand::title), "테스트 게시글 제목")
            .set(KSelect.field(CreateArticleCommand::content), "테스트 게시글 내용")
            .set(KSelect.field(CreateArticleCommand::boardId), boardId)
            .set(KSelect.field(CreateArticleCommand::writerId), writerId)
            .create()
    }

    fun getUpdateArticleCommand(): UpdateArticleCommand {
        return Instancio.of(UpdateArticleCommand::class.java)
            .set(KSelect.field(UpdateArticleCommand::id), 1L)
            .set(KSelect.field(UpdateArticleCommand::title), "수정된 게시글 제목")
            .set(KSelect.field(UpdateArticleCommand::content), "수정된 게시글 내용")
            .create()
    }

    fun getUpdateArticleCommandWithId(id: Long): UpdateArticleCommand {
        return Instancio.of(UpdateArticleCommand::class.java)
            .set(KSelect.field(UpdateArticleCommand::id), id)
            .set(KSelect.field(UpdateArticleCommand::title), "수정된 게시글 제목")
            .set(KSelect.field(UpdateArticleCommand::content), "수정된 게시글 내용")
            .create()
    }

    fun getArticle(): Article {
        return ArticleDomainFixtures.getArticle()
    }

    fun getArticleWithId(id: Long): Article {
        return ArticleDomainFixtures.getArticleWithId(id)
    }

    fun getArticleList(): List<Article> {
        return listOf(
            ArticleDomainFixtures.getArticleWithId(1L),
            ArticleDomainFixtures.getArticleWithId(2L),
            ArticleDomainFixtures.getArticleWithId(3L)
        )
    }

    fun getArticleListWithBoardId(boardId: Long): List<Article> {
        return listOf(
            ArticleDomainFixtures.getArticleWithBoardId(boardId),
            ArticleDomainFixtures.getArticleWithBoardId(boardId),
            ArticleDomainFixtures.getArticleWithBoardId(boardId)
        )
    }

    // viewCount는 존재하지 않으므로 단순히 Article을 반환
    fun getArticleWithViewCount(id: Long, viewCount: Long): Article {
        return ArticleDomainFixtures.getArticleWithId(id)
    }
}

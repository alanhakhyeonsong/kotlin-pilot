package me.ramos.article.application.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import me.ramos.article.application.fixture.ArticleApplicationFixtures
import me.ramos.article.domain.fixture.ArticleDomainFixtures

/**
 * ArticleService 통합 테스트
 *
 * @author HakHyeon Song
 */
class ArticleServiceTest : BehaviorSpec({

    Given("통합 게시글 서비스가") {
        When("게시글 조회를 요청할 때") {
            val articleQueryService = mockk<ArticleQueryService>()
            val articleCommandService = mockk<ArticleCommandService>()
            val articleService = ArticleService(articleQueryService, articleCommandService)
            val articleId = 1L
            val expectedArticle = ArticleDomainFixtures.getArticleWithId(articleId)

            every { articleQueryService.getArticle(articleId) } returns expectedArticle

            val result = articleService.getArticle(articleId)

            Then("QueryService에 위임하여 게시글을 반환한다") {
                result shouldBe expectedArticle

                verify(exactly = 1) { articleQueryService.getArticle(articleId) }
            }
        }

        When("전체 게시글 조회를 요청할 때") {
            val articleQueryService = mockk<ArticleQueryService>()
            val articleCommandService = mockk<ArticleCommandService>()
            val articleService = ArticleService(articleQueryService, articleCommandService)
            val expectedArticles = ArticleApplicationFixtures.getArticleList()

            every { articleQueryService.getAllArticles() } returns expectedArticles

            val result = articleService.getAllArticles()

            Then("QueryService에 위임하여 전체 게시글을 반환한다") {
                result shouldBe expectedArticles

                verify(exactly = 1) { articleQueryService.getAllArticles() }
            }
        }

        When("게시판별 게시글 조회를 요청할 때") {
            val articleQueryService = mockk<ArticleQueryService>()
            val articleCommandService = mockk<ArticleCommandService>()
            val articleService = ArticleService(articleQueryService, articleCommandService)
            val boardId = 1L
            val expectedArticles = ArticleApplicationFixtures.getArticleListWithBoardId(boardId)

            every { articleQueryService.getArticlesByBoard(boardId) } returns expectedArticles

            val result = articleService.getArticlesByBoard(boardId)

            Then("QueryService에 위임하여 게시판별 게시글을 반환한다") {
                result shouldBe expectedArticles

                verify(exactly = 1) { articleQueryService.getArticlesByBoard(boardId) }
            }
        }

        When("작성자별 게시글 조회를 요청할 때") {
            val articleQueryService = mockk<ArticleQueryService>()
            val articleCommandService = mockk<ArticleCommandService>()
            val articleService = ArticleService(articleQueryService, articleCommandService)
            val writerId = 1L
            val expectedArticles = listOf(ArticleDomainFixtures.getArticleWithWriterId(writerId))

            every { articleQueryService.getArticlesByWriter(writerId) } returns expectedArticles

            val result = articleService.getArticlesByWriter(writerId)

            Then("QueryService에 위임하여 작성자별 게시글을 반환한다") {
                result shouldBe expectedArticles

                verify(exactly = 1) { articleQueryService.getArticlesByWriter(writerId) }
            }
        }

        When("게시글 생성을 요청할 때") {
            val articleQueryService = mockk<ArticleQueryService>()
            val articleCommandService = mockk<ArticleCommandService>()
            val articleService = ArticleService(articleQueryService, articleCommandService)
            val command = ArticleApplicationFixtures.getCreateArticleCommand()
            val expectedArticle = ArticleDomainFixtures.getArticleWithId(1L)

            every { articleCommandService.createArticle(command) } returns expectedArticle

            val result = articleService.createArticle(command)

            Then("CommandService에 위임하여 게시글을 생성한다") {
                result shouldBe expectedArticle

                verify(exactly = 1) { articleCommandService.createArticle(command) }
            }
        }

        When("게시글 수정을 요청할 때") {
            val articleQueryService = mockk<ArticleQueryService>()
            val articleCommandService = mockk<ArticleCommandService>()
            val articleService = ArticleService(articleQueryService, articleCommandService)
            val command = ArticleApplicationFixtures.getUpdateArticleCommandWithId(1L)
            val expectedArticle = ArticleDomainFixtures.getArticleWithId(1L)

            every { articleCommandService.updateArticle(command) } returns expectedArticle

            val result = articleService.updateArticle(command)

            Then("CommandService에 위임하여 게시글을 수정한다") {
                result shouldBe expectedArticle

                verify(exactly = 1) { articleCommandService.updateArticle(command) }
            }
        }

        When("게시글 삭제를 요청할 때") {
            val articleQueryService = mockk<ArticleQueryService>()
            val articleCommandService = mockk<ArticleCommandService>()
            val articleService = ArticleService(articleQueryService, articleCommandService)
            val articleId = 1L

            every { articleCommandService.deleteArticle(articleId) } returns Unit

            articleService.deleteArticle(articleId)

            Then("CommandService에 위임하여 게시글을 삭제한다") {
                verify(exactly = 1) { articleCommandService.deleteArticle(articleId) }
            }
        }
    }
})

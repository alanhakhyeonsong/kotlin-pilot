package me.ramos.article.application.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.comparables.shouldBeLessThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import me.ramos.article.application.fixture.ArticleApplicationFixtures
import me.ramos.article.application.port.out.ArticleQueryPort
import me.ramos.article.domain.fixture.ArticleDomainFixtures

/**
 * ArticleQueryService 테스트
 *
 * @author HakHyeon Song
 */
class ArticleQueryServiceTest : BehaviorSpec({

    Given("게시글 조회 서비스가") {
        When("존재하는 게시글 ID로 조회할 때") {
            val articleQueryPort = mockk<ArticleQueryPort>()
            val articleQueryService = ArticleQueryService(articleQueryPort)
            val articleId = 1L
            val expectedArticle = ArticleDomainFixtures.getArticleWithId(articleId)

            every { articleQueryPort.loadArticle(articleId) } returns expectedArticle

            val result = articleQueryService.getArticle(articleId)

            Then("해당 게시글이 반환된다") {
                result shouldNotBe null
                result?.id shouldBe articleId
                result?.title shouldBe expectedArticle.title
                result?.content shouldBe expectedArticle.content

                verify(exactly = 1) { articleQueryPort.loadArticle(articleId) }
            }
        }

        When("존재하지 않는 게시글 ID로 조회할 때") {
            val articleQueryPort = mockk<ArticleQueryPort>()
            val articleQueryService = ArticleQueryService(articleQueryPort)
            val nonExistentId = 999L

            every { articleQueryPort.loadArticle(nonExistentId) } returns null

            val result = articleQueryService.getArticle(nonExistentId)

            Then("null이 반환된다") {
                result shouldBe null

                verify(exactly = 1) { articleQueryPort.loadArticle(nonExistentId) }
            }
        }
    }

    Given("무한 스크롤 게시글 조회 시") {
        When("첫 번째 페이지를 요청할 때 (lastArticleId = null)") {
            val articleQueryPort = mockk<ArticleQueryPort>()
            val articleQueryService = ArticleQueryService(articleQueryPort)
            val boardId = 1L
            val pageSize = 10L
            val lastArticleId: Long? = null
            val expectedArticles = ArticleApplicationFixtures.getArticleList()

            every { articleQueryPort.loadAllArticles(boardId, pageSize, lastArticleId) } returns expectedArticles

            val result = articleQueryService.getAllArticles(boardId, pageSize, lastArticleId)

            Then("첫 번째 페이지의 게시글들이 반환된다") {
                result shouldBe expectedArticles
                result.size shouldBe 3

                verify(exactly = 1) { articleQueryPort.loadAllArticles(boardId, pageSize, lastArticleId) }
            }
        }

        When("두 번째 페이지를 요청할 때 (lastArticleId가 있음)") {
            val articleQueryPort = mockk<ArticleQueryPort>()
            val articleQueryService = ArticleQueryService(articleQueryPort)
            val boardId = 1L
            val pageSize = 10L
            val lastArticleId = 10L
            val expectedArticles = listOf(
                ArticleDomainFixtures.getArticleWithId(9L),
                ArticleDomainFixtures.getArticleWithId(8L),
                ArticleDomainFixtures.getArticleWithId(7L)
            )

            every { articleQueryPort.loadAllArticles(boardId, pageSize, lastArticleId) } returns expectedArticles

            val result = articleQueryService.getAllArticles(boardId, pageSize, lastArticleId)

            Then("lastArticleId 이후의 게시글들이 반환된다") {
                result shouldBe expectedArticles
                result.size shouldBe 3
                // 무한 스크롤에서는 ID가 내림차순으로 정렬되어야 함
                result.forEach { article ->
                    article.id!! shouldBeLessThan lastArticleId
                }

                verify(exactly = 1) { articleQueryPort.loadAllArticles(boardId, pageSize, lastArticleId) }
            }
        }

        When("마지막 페이지를 요청할 때") {
            val articleQueryPort = mockk<ArticleQueryPort>()
            val articleQueryService = ArticleQueryService(articleQueryPort)
            val boardId = 1L
            val pageSize = 10L
            val lastArticleId = 3L

            every { articleQueryPort.loadAllArticles(boardId, pageSize, lastArticleId) } returns emptyList()

            val result = articleQueryService.getAllArticles(boardId, pageSize, lastArticleId)

            Then("빈 리스트가 반환된다") {
                result shouldBe emptyList()

                verify(exactly = 1) { articleQueryPort.loadAllArticles(boardId, pageSize, lastArticleId) }
            }
        }

        When("페이지 크기보다 적은 게시글이 있을 때") {
            val articleQueryPort = mockk<ArticleQueryPort>()
            val articleQueryService = ArticleQueryService(articleQueryPort)
            val boardId = 1L
            val pageSize = 10L
            val lastArticleId: Long? = null
            val expectedArticles = listOf(
                ArticleDomainFixtures.getArticleWithId(5L),
                ArticleDomainFixtures.getArticleWithId(4L)
            )

            every { articleQueryPort.loadAllArticles(boardId, pageSize, lastArticleId) } returns expectedArticles

            val result = articleQueryService.getAllArticles(boardId, pageSize, lastArticleId)

            Then("실제 존재하는 게시글만 반환된다") {
                result shouldBe expectedArticles
                result.size shouldBe 2

                verify(exactly = 1) { articleQueryPort.loadAllArticles(boardId, pageSize, lastArticleId) }
            }
        }
    }

    Given("게시판별 게시글 조회 시") {
        When("해당 게시판에 게시글이 존재할 때") {
            val articleQueryPort = mockk<ArticleQueryPort>()
            val articleQueryService = ArticleQueryService(articleQueryPort)
            val boardId = 1L
            val expectedArticles = ArticleApplicationFixtures.getArticleListWithBoardId(boardId)

            every { articleQueryPort.loadArticlesByBoard(boardId) } returns expectedArticles

            val result = articleQueryService.getArticlesByBoard(boardId)

            Then("해당 게시판의 게시글들이 반환된다") {
                result shouldBe expectedArticles
                result.size shouldBe 3
                result.forEach { it.boardId shouldBe boardId }

                verify(exactly = 1) { articleQueryPort.loadArticlesByBoard(boardId) }
            }
        }
    }

    Given("작성자별 게시글 조회 시") {
        When("해당 작성자의 게시글이 존재할 때") {
            val articleQueryPort = mockk<ArticleQueryPort>()
            val articleQueryService = ArticleQueryService(articleQueryPort)
            val writerId = 1L
            val expectedArticles = listOf(
                ArticleDomainFixtures.getArticleWithWriterId(writerId),
                ArticleDomainFixtures.getArticleWithWriterId(writerId)
            )

            every { articleQueryPort.loadArticlesByWriter(writerId) } returns expectedArticles

            val result = articleQueryService.getArticlesByWriter(writerId)

            Then("해당 작성자의 게시글들이 반환된다") {
                result shouldBe expectedArticles
                result.size shouldBe 2
                result.forEach { it.writerId shouldBe writerId }

                verify(exactly = 1) { articleQueryPort.loadArticlesByWriter(writerId) }
            }
        }
    }
})

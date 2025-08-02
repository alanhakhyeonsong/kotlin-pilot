package me.ramos.article.application.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import me.ramos.article.application.fixture.ArticleApplicationFixtures
import me.ramos.article.application.port.out.ArticlePort
import me.ramos.article.domain.fixture.ArticleDomainFixtures

/**
 * ArticleQueryService 테스트
 *
 * @author HakHyeon Song
 */
class ArticleQueryServiceTest : BehaviorSpec({

    val articlePort = mockk<ArticlePort>()
    val articleQueryService = ArticleQueryService(articlePort)

    Given("게시글 조회 서비스가") {
        When("존재하는 게시글 ID로 조회할 때") {
            val articleId = 1L
            val expectedArticle = ArticleDomainFixtures.getArticleWithId(articleId)
            every { articlePort.loadArticle(articleId) } returns expectedArticle

            val result = articleQueryService.getArticle(articleId)

            Then("해당 게시글이 반환된다") {
                result shouldNotBe null
                result?.id shouldBe articleId
                result?.title shouldBe expectedArticle.title
                result?.content shouldBe expectedArticle.content
            }

            Then("LoadArticlePort가 호출된다") {
                verify { articlePort.loadArticle(articleId) }
            }
        }

        When("존재하지 않는 게시글 ID로 조회할 때") {
            val nonExistentId = 999L
            every { articlePort.loadArticle(nonExistentId) } returns null

            val result = articleQueryService.getArticle(nonExistentId)

            Then("null이 반환된다") {
                result shouldBe null
            }

            Then("LoadArticlePort가 호출된다") {
                verify { articlePort.loadArticle(nonExistentId) }
            }
        }
    }

    Given("전체 게시글 조회 시") {
        When("게시글이 존재할 때") {
            val expectedArticles = ArticleApplicationFixtures.getArticleList()
            every { articlePort.loadAllArticles() } returns expectedArticles

            val result = articleQueryService.getAllArticles()

            Then("모든 게시글이 반환된다") {
                result shouldBe expectedArticles
                result.size shouldBe 3
            }

            Then("LoadArticlePort가 호출된다") {
                verify { articlePort.loadAllArticles() }
            }
        }

        When("게시글이 존재하지 않을 때") {
            every { articlePort.loadAllArticles() } returns emptyList()

            val result = articleQueryService.getAllArticles()

            Then("빈 리스트가 반환된다") {
                result shouldBe emptyList()
            }
        }
    }

    Given("특정 게시판의 게시글을 조회할 때") {
        val boardId = 1L

        When("해당 게시판에 게시글이 존재할 때") {
            val expectedArticles = ArticleApplicationFixtures.getArticleListWithBoardId(boardId)
            every { articlePort.loadArticlesByBoard(boardId) } returns expectedArticles

            val result = articleQueryService.getArticlesByBoard(boardId)

            Then("해당 게시판의 게시글들이 반환된다") {
                result shouldBe expectedArticles
                result.size shouldBe 2
                result.forEach { article ->
                    article.boardId shouldBe boardId
                }
            }

            Then("LoadArticlePort가 호출된다") {
                verify { articlePort.loadArticlesByBoard(boardId) }
            }
        }

        When("해당 게시판에 게시글이 존재하지 않을 때") {
            every { articlePort.loadArticlesByBoard(boardId) } returns emptyList()

            val result = articleQueryService.getArticlesByBoard(boardId)

            Then("빈 리스트가 반환된다") {
                result shouldBe emptyList()
            }
        }
    }

    Given("특정 작성자의 게시글을 조회할 때") {
        val writerId = 1L

        When("해당 작성자의 게시글이 존재할 때") {
            val expectedArticles = listOf(
                ArticleDomainFixtures.getArticleWithWriterId(writerId),
                ArticleDomainFixtures.getArticleWithWriterId(writerId)
            )
            every { articlePort.loadArticlesByWriter(writerId) } returns expectedArticles

            val result = articleQueryService.getArticlesByWriter(writerId)

            Then("해당 작성자의 게시글들이 반환된다") {
                result shouldBe expectedArticles
                result.size shouldBe 2
                result.forEach { article ->
                    article.writerId shouldBe writerId
                }
            }

            Then("LoadArticlePort가 호출된다") {
                verify { articlePort.loadArticlesByWriter(writerId) }
            }
        }
    }
})

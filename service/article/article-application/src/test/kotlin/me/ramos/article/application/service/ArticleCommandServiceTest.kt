package me.ramos.article.application.service

import io.kotest.assertions.throwables.shouldThrow
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
 * ArticleCommandService 테스트
 *
 * @author HakHyeon Song
 */
class ArticleCommandServiceTest : BehaviorSpec({

    val articlePort = mockk<ArticlePort>()
    val articleCommandService = ArticleCommandService(articlePort)

    Given("게시글 생성 명령이") {
        When("유효한 정보로 전달될 때") {
            val command = ArticleApplicationFixtures.getCreateArticleCommand()
            val expectedArticle = ArticleDomainFixtures.getArticleWithId(1L)
            every { articlePort.saveArticle(any()) } returns expectedArticle

            val result = articleCommandService.createArticle(command)

            Then("게시글이 생성되고 반환된다") {
                result shouldBe expectedArticle
                result.id shouldBe 1L
                result.title shouldBe command.title
                result.content shouldBe command.content
                result.boardId shouldBe command.boardId
                result.writerId shouldBe command.writerId
            }

            Then("SaveArticlePort가 호출된다") {
                verify { articlePort.saveArticle(any()) }
            }
        }
    }

    Given("게시글 수정 명령이") {
        When("존재하는 게시글에 대해 전달될 때") {
            val command = ArticleApplicationFixtures.getUpdateArticleCommandWithId(1L)
            val existingArticle = ArticleDomainFixtures.getArticleWithId(1L)
            val updatedArticle = existingArticle.update(command.title, command.content)

            every { articlePort.loadArticle(command.id) } returns existingArticle
            every { articlePort.saveArticle(any()) } returns updatedArticle

            val result = articleCommandService.updateArticle(command)

            Then("게시글이 수정되고 반환된다") {
                result.id shouldBe command.id
                result.title shouldBe command.title
                result.content shouldBe command.content
                result.modifiedAt shouldNotBe existingArticle.modifiedAt
            }

            Then("ArticlePort가 호출된다") {
                verify { articlePort.loadArticle(command.id) }
                verify { articlePort.saveArticle(any()) }
            }
        }

        When("존재하지 않는 게시글에 대해 전달될 때") {
            val command = ArticleApplicationFixtures.getUpdateArticleCommandWithId(999L)
            every { articlePort.loadArticle(command.id) } returns null

            Then("IllegalArgumentException이 발생한다") {
                val exception = shouldThrow<IllegalArgumentException> {
                    articleCommandService.updateArticle(command)
                }
                exception.message shouldBe "Article not found with id: ${command.id}"
            }

            Then("loadArticle만 호출되고 saveArticle은 호출되지 않는다") {
                verify { articlePort.loadArticle(command.id) }
                verify(exactly = 0) { articlePort.saveArticle(any()) }
            }
        }
    }

    Given("게시글 삭제 명령이") {
        When("존재하는 게시글에 대해 전달될 때") {
            val articleId = 1L
            every { articlePort.existsArticle(articleId) } returns true
            every { articlePort.deleteArticle(articleId) } returns Unit

            articleCommandService.deleteArticle(articleId)

            Then("ArticlePort가 호출된다") {
                verify { articlePort.existsArticle(articleId) }
                verify { articlePort.deleteArticle(articleId) }
            }
        }

        When("존재하지 않는 게시글에 대해 전달될 때") {
            val nonExistentId = 999L
            every { articlePort.existsArticle(nonExistentId) } returns false

            Then("IllegalArgumentException이 발생한다") {
                val exception = shouldThrow<IllegalArgumentException> {
                    articleCommandService.deleteArticle(nonExistentId)
                }
                exception.message shouldBe "Article not found with id: $nonExistentId"
            }

            Then("existsArticle만 호출되고 deleteArticle은 호출되지 않는다") {
                verify { articlePort.existsArticle(nonExistentId) }
                verify(exactly = 0) { articlePort.deleteArticle(any()) }
            }
        }
    }
})

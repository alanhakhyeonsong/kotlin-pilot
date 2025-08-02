package me.ramos.article.domain.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import me.ramos.article.domain.fixture.ArticleDomainFixtures

/**
 * Article 도메인 모델 테스트
 *
 * @author HakHyeon Song
 */
class ArticleTest : BehaviorSpec({

    Given("게시글을") {
        val originalArticle = ArticleDomainFixtures.getArticleWithTitleAndContent("원본 제목", "원본 내용")

        When("제목과 내용을 수정할 때") {
            val newTitle = "수정된 제목"
            val newContent = "수정된 내용"
            val result = originalArticle.update(newTitle, newContent)

            Then("제목과 내용이 변경되고 수정시간이 업데이트된다") {
                result.title shouldBe newTitle
                result.content shouldBe newContent
                result.modifiedAt shouldNotBe originalArticle.modifiedAt
                result.modifiedAt shouldNotBe null
            }

            Then("다른 필드들은 변경되지 않는다") {
                result.id shouldBe originalArticle.id
                result.boardId shouldBe originalArticle.boardId
                result.writerId shouldBe originalArticle.writerId
                result.createdAt shouldBe originalArticle.createdAt
            }
        }
    }
})

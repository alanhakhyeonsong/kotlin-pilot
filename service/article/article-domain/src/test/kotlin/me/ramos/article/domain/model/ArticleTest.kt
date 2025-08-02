package me.ramos.article.domain.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

/**
 * Article 도메인 모델 테스트
 *
 * @author HakHyeon Song
 */
class ArticleTest : BehaviorSpec({

    Given("게시글이") {
        val article = ArticleDomainFixtures.getArticleWithViewCount(5L)

        When("조회수를 증가시킬 때") {
            val result = article.increaseViewCount()

            Then("조회수가 1 증가한다") {
                result.viewCount shouldBe 6L
                result.id shouldBe article.id
                result.title shouldBe article.title
                result.content shouldBe article.content
            }
        }
    }

    Given("게시글의 좋아요 수가") {
        When("0일 때 좋아요를 증가시키면") {
            val article = ArticleDomainFixtures.getArticleWithLikeCount(0L)
            val result = article.increaseLikeCount()

            Then("좋아요 수가 1이 된다") {
                result.likeCount shouldBe 1L
            }
        }

        When("5일 때 좋아요를 증가시키면") {
            val article = ArticleDomainFixtures.getArticleWithLikeCount(5L)
            val result = article.increaseLikeCount()

            Then("좋아요 수가 6이 된다") {
                result.likeCount shouldBe 6L
            }
        }
    }

    Given("게시글의 좋아요 수가") {
        When("5일 때 좋아요를 감소시키면") {
            val article = ArticleDomainFixtures.getArticleWithLikeCount(5L)
            val result = article.decreaseLikeCount()

            Then("좋아요 수가 4가 된다") {
                result.likeCount shouldBe 4L
            }
        }

        When("0일 때 좋아요를 감소시키면") {
            val article = ArticleDomainFixtures.getArticleWithLikeCount(0L)
            val result = article.decreaseLikeCount()

            Then("좋아요 수가 0으로 유지된다") {
                result.likeCount shouldBe 0L
            }
        }
    }

    Given("게시글을") {
        val originalArticle = ArticleDomainFixtures.getArticleWithTitleAndContent("원본 제목", "원본 내용")

        When("제목과 내용을 수정할 때") {
            val newTitle = "수정된 제목"
            val newContent = "수정된 내용"
            val result = originalArticle.update(newTitle, newContent)

            Then("제목과 내용이 변경되고 수정시간이 업데이트된다") {
                result.title shouldBe newTitle
                result.content shouldBe newContent
                result.updatedAt shouldNotBe originalArticle.updatedAt
                result.updatedAt shouldNotBe null
            }

            Then("다른 필드들은 변경되지 않는다") {
                result.id shouldBe originalArticle.id
                result.boardId shouldBe originalArticle.boardId
                result.writerId shouldBe originalArticle.writerId
                result.viewCount shouldBe originalArticle.viewCount
                result.likeCount shouldBe originalArticle.likeCount
                result.createdAt shouldBe originalArticle.createdAt
            }
        }
    }
})

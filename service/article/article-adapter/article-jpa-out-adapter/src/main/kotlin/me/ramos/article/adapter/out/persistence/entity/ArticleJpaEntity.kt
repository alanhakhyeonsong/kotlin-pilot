package me.ramos.article.adapter.out.persistence.entity

import jakarta.persistence.*
import me.ramos.article.domain.model.Article
import me.ramos.core.jpa.model.BaseEntity
import java.time.LocalDateTime

/**
 * 게시글 JPA 엔티티
 * 데이터베이스 테이블과 매핑되는 게시글 영속성 객체입니다.
 *
 * @author HakHyeon Song
 */
@Entity
@Table(
    name = "article",
    indexes = [
        Index(name = "idx_board_id_article_id", columnList = "board_id ASC, article_id DESC")
    ]
)
class ArticleJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    var articleId: Long? = null,

    @Column(name = "title", nullable = false, length = 100)
    var title: String,

    @Column(name = "content", nullable = false, length = 3000)
    var content: String,

    @Column(name = "board_id", nullable = false)
    var boardId: Long,

    @Column(name = "writer_id", nullable = false)
    var writerId: Long,
) : BaseEntity() {

    fun toDomain(): Article {
        return Article(
            id = this.articleId,
            title = this.title,
            content = this.content,
            boardId = this.boardId,
            writerId = this.writerId,
            createdAt = this.createdAt,
            modifiedAt = this.modifiedAt
        )
    }

    companion object {
        fun fromDomain(article: Article): ArticleJpaEntity {
            return ArticleJpaEntity(
                articleId = article.id,
                title = article.title,
                content = article.content,
                boardId = article.boardId,
                writerId = article.writerId
            )
        }
    }
}

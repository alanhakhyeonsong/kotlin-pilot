package me.ramos.article.adapter.out.persistence.entity

import jakarta.persistence.*
import me.ramos.article.domain.model.Article
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

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "modified_at", nullable = false)
    var modifiedAt: LocalDateTime = LocalDateTime.now()
) {

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

    fun updateFromDomain(article: Article) {
        this.title = article.title
        this.content = article.content
        this.boardId = article.boardId
        this.writerId = article.writerId
        this.modifiedAt = LocalDateTime.now()
    }

    companion object {
        fun fromDomain(article: Article): ArticleJpaEntity {
            return ArticleJpaEntity(
                articleId = article.id,
                title = article.title,
                content = article.content,
                boardId = article.boardId,
                writerId = article.writerId,
                createdAt = article.createdAt ?: LocalDateTime.now(),
                modifiedAt = article.modifiedAt ?: LocalDateTime.now()
            )
        }
    }
}

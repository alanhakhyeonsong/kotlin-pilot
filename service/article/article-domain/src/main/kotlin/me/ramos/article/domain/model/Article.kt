package me.ramos.article.domain.model

import java.time.LocalDateTime

/**
 * 게시글 도메인 엔티티
 * 게시글의 핵심 비즈니스 로직과 데이터를 캡슐화합니다.
 *
 * @author HakHyeon Song
 */
data class Article(
    val id: Long? = null,
    val title: String,
    val content: String,
    val boardId: Long,
    val writerId: Long,
    val createdAt: LocalDateTime? = null,
    val modifiedAt: LocalDateTime? = null
) {
    fun update(title: String, content: String): Article {
        return this.copy(
            title = title,
            content = content,
            modifiedAt = LocalDateTime.now()
        )
    }
}

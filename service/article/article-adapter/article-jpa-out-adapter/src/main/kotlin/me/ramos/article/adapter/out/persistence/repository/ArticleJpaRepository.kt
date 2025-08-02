package me.ramos.article.adapter.out.persistence.repository

import me.ramos.article.adapter.out.persistence.entity.ArticleJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * 게시글 JPA 리포지토리
 * 게시글 데이터의 데이터베이스 접근을 담당합니다.
 *
 * @author HakHyeon Song
 */
@Repository
interface ArticleJpaRepository : JpaRepository<ArticleJpaEntity, Long> {
    fun findByBoardIdOrderByArticleIdDesc(boardId: Long): List<ArticleJpaEntity>
    fun findByWriterIdOrderByArticleIdDesc(writerId: Long): List<ArticleJpaEntity>
    fun findAllByOrderByArticleIdDesc(): List<ArticleJpaEntity>
}

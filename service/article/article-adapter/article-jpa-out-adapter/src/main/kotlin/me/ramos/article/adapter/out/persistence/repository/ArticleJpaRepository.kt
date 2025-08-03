package me.ramos.article.adapter.out.persistence.repository

import me.ramos.article.adapter.out.persistence.entity.ArticleJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
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

    @Query(
        value = ("select article.article_id, article.title, article.content, article.board_id, article.writer_id, " +
                "article.created_at, article.modified_at " +
                "from article " +
                "where board_id = :boardId " +
                "order by article_id desc limit :limit"),
        nativeQuery = true
    )
    fun findAllInfiniteScroll(
        @Param("boardId") boardId: Long,
        @Param("limit") limit: Long,
    ): List<ArticleJpaEntity>

    @Query(
        value = ("select article.article_id, article.title, article.content, article.board_id, article.writer_id, " +
                "article.created_at, article.modified_at " +
                "from article " +
                "where board_id = :boardId and article_id < :lastArticleId " +
                "order by article_id desc limit :limit"),
        nativeQuery = true
    )
    fun findAllInfiniteScroll(
        @Param("boardId") boardId: Long,
        @Param("limit") limit: Long,
        @Param("lastArticleId") lastArticleId: Long
    ): List<ArticleJpaEntity>
}

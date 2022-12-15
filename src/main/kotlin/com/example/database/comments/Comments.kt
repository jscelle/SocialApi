package com.example.database.comments

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import javax.swing.text.html.parser.Entity

object Comments: IntIdTable() {
    val authorId = Comments.integer("authorId")
    val newsId = Comments.integer("newsId")
    val content = Comments.varchar("content", 50)

    fun insert(comment: CommentsDTO) {
        transaction {
            Comments.insert {
                it[authorId] = comment.authorId
                it[newsId] = comment.newsId
                it[content] = comment.content
            }
        }
    }

    fun getById(newsId: Int): List<CommentsDTO> {

        val comments = transaction {
            Comments.select {
                Comments.newsId.eq(newsId)
            }.map {
                CommentsDTO(
                    authorId = it[authorId],
                    newsId = it[Comments.newsId],
                    content = it[content]
                )
            }
        }

        return comments
    }
}
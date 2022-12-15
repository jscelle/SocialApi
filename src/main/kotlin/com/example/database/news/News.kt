package com.example.database.news

import com.example.database.comments.Comments
import com.example.database.users.UserDTO
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object News: IntIdTable() {
    val title = varchar("title", 25)
    val description = varchar("description", 100)
    val underReview = bool("underReview")

    fun getNews(reviewed: Boolean): List<NewsDTO> {
        val news = transaction {
            News.select {
                News.underReview.eq(reviewed)
            }.map {
                NewsDTO(
                    title = it[News.title],
                    id = it[News.id].value,
                    description = it[News.description]
                )
            }
        }

        return news
    }

    fun postNews(news: NewsDTO, underReview: Boolean) {
        transaction {
            News.insert {
                it[title] = news.title
                it[description] = news.description
                it[News.underReview] = underReview
            }
        }
    }
}
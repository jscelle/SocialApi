package com.example.feature.news

import com.example.database.news.News
import com.example.database.news.NewsDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class NewsManager {
    suspend fun getNews(
        call: ApplicationCall,
        reviewed: Boolean
    ) {
        val news = News.getNews(reviewed = reviewed)
            .map {
                NewsOutput(
                    title = it.title,
                    description = it.description,
                    id = it.id
                )
            }

        call.respond(news)
    }

    suspend fun postNews(
        call: ApplicationCall,
        reviewed: Boolean
    ) {
        val news = call.receive<NewsInput>()

        News.postNews(
            news = NewsDTO(
                title = news.title,
                description = news.description
            ),
            reviewed
        )

        call.respond(HttpStatusCode.Created)
    }
}
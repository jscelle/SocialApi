
import com.example.feature.news.NewsManager
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Application.configureNewsRouting() {

    val newsManager = NewsManager()

    routing {
        authenticate("auth-basic") {
            get("/news/reviewed") {
                newsManager.getNews(call = call, reviewed = true)
            }
            post("/news/underreview") {
                newsManager.postNews(call = call, reviewed = false)
            }
        }
    }
    routing {
        authenticate("admin") {
            get("/news/underreview") {
                newsManager.getNews(call = call, reviewed = false)
            }
            post("/news/reviewed") {
                newsManager.postNews(call = call, reviewed = true)
            }
        }
    }
}
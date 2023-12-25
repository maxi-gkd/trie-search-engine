package com.example

import com.example.engine.Trie
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.cors.routing.CORS
import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun Application.module() {
    val log: Logger = LoggerFactory.getLogger(Application::class.java)

    install(ContentNegotiation) {
        jackson { }
    }

    install(StatusPages) {
        exception<NotFoundException> { call, _ ->
            call.respond(HttpStatusCode.NotFound)
            log.error("NotFoundException: ${call.request.path()}" + " " + call.request.queryParameters["query"])
        }
    }

    install(CORS) {
        allowHost("localhost:3000", schemes = listOf("http"))
    }

    routing {
        val trie = Trie()

        route("/add-word") {
            post {
                val wordRequest = call.receive<WordRequest>()
                val word = wordRequest.word

                trie.insert(word)
                call.respond(HttpStatusCode.Created)
                log.info("Word '$word' added successfully.")
            }
        }

        route("/search") {
            get {
                val query = call.request.queryParameters["query"]
                if (query != null && trie.search(query)) {
                    log.info("Word '$query' found.")
                    call.respond(mapOf("result" to "Found"))
                } else {
                    throw NotFoundException()
                }
            }
        }
    }
}

class NotFoundException : RuntimeException()
data class WordRequest(val word: String)

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}

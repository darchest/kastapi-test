package org.test

import com.typesafe.config.ConfigFactory
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.darchest.kastapi.generated.apiRoutes
import org.darchest.kastapi.ktor.utility.KtorUtility
import java.io.File

fun Route.test() {
    route("/test") {
        get("fdsfds/{id}") {
            val multipart = call.receiveMultipart()
            var arg: String? = null
            multipart.forEachPart { part ->
                when (part.name) {
                    "test" -> arg = "sdadsa"
                }
            }
            if (arg == null)
                return@get
            val test = arg
            KtorUtility.getOptionalPathParameter(call, "id", String::class)
            val arg0 = (call.parameters["id"] ?: throw IllegalArgumentException("Missing required path parameter 'id'"))?.toInt()
        }
    }
}

fun main(): Unit {
    embeddedServer(Netty, port = 8080, module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson()
    }
}

fun Application.configureRouting() {
    routing {
        staticFiles("/", File("web")) {
            default("index.html")
        }
        route("api") {
            apiRoutes()
        }
        swaggerUI(path = "swagger", swaggerFile = "openapi.yaml")
    }
}
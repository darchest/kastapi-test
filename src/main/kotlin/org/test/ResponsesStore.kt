package org.test

import io.ktor.http.*
import io.ktor.utils.io.core.*
import org.darchest.kastapi.annotations.*
import org.darchest.kastapi.ktor.utility.InMemoryFile
import org.darchest.kastapi.ktor.utility.Wrapper
import kotlin.text.toByteArray

/*
Любой возвращаемый тип функции будет сконвертирован в ответ
По умолчанию при успешном выполнении возвращается код 200,
но это поведение можно переопределить аннотацией @CodeOnSuccess
Если функция возвращает Pair<Int, *>, то Int будет воспринят как возвращаемый код
Если функция возвращает InMemoryFile, то он будет возвращён как файл с обозначенным именем и типом
 */

@Routes("responses")
@PackageName("api")
@RemoveWrappers(EmptyWrapper::class)
class ResponsesStore {

    @Get
    @CodeOnSuccess(201)
    fun code201(): String {
        return "OK"
    }

    class Response(val success: Boolean, val message: String)

    @Get
    fun someObject(): Response {
        return Response(true, "Test message")
    }

    @Get
    fun code202(): Pair<Int, String> {
        return Pair(202, "OK")
    }

    @Get
    fun file(): InMemoryFile {
        return InMemoryFile("test.txt", ContentType.Text.Plain, "Hello world".toByteArray())
    }

    @Get
    fun fileAndCode(): Pair<Int, InMemoryFile> {
        return Pair(201, InMemoryFile("test.txt", ContentType.Text.Plain, "Hello world".toByteArray()))
    }
}
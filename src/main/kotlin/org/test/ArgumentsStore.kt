package org.test

import io.ktor.server.application.*
import io.ktor.server.request.*
import org.darchest.kastapi.annotations.*
import org.darchest.kastapi.ktor.utility.InMemoryFile
import org.darchest.kastapi.ktor.utility.Wrapper

/*
Каждый аргумент функции будет извлечён из Http запроса автоматически
По умолчанию каждый аргумент извлекается из сегмента пути URL
Аннотация @Query указывает, что аргумент следует взять из части запроса (после '?')
Аннотация @Body указывает, что аргумент следует извлечь из тела запроса
Аннотация @Form указывает, что аргумент следует извлечь из form параметра запроса
Аннотация @Multipart указывает, что аргумент следует извлечь из form параметра запроса
Если аргумент может быть null (например String?), то он считается опциональным
Если аргумент не может быть null (String), то будет выброшено исключение, если он не будет получен
Для получения файла из Multipart следует использовать класс InMemoryFile
 */

@Routes("arguments")
@PackageName("api")
@RemoveWrappers(EmptyWrapper::class)
@Tags("Basic")
class ArgumentsStore {

    @Get("pathArgs/{requiredNumber}/{optionalString}")
    fun pathArgs(requiredNumber: Int, optionalString: String?): String {
        return "Number = $requiredNumber, string = $optionalString"
    }

    @Get
    fun queryArgs(@Query number: Int): String {
        return "Number = $number"
    }

    class BodyData(val number: Int, val string: String)

    @Post
    fun body(@Body body: BodyData): String {
        return "Number = ${body.number}, string = ${body.string}"
    }

    @Post
    fun form(@Form number: Int, @Form title: String?): String {
        return "Number = $number, title = $title"
    }

    @Post
    fun multipart(@Multipart number: Int, @Multipart file: InMemoryFile): String {
        return "Number = $number, file = { name: ${file.name}, size: ${file.data.size} }"
    }

    @Get
    fun callArg(call: ApplicationCall): String {
        return "Request URI = ${call.request.uri}"
    }
}
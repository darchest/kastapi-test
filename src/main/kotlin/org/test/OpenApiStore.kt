package org.test

import io.ktor.http.*
import io.ktor.utils.io.core.*
import org.darchest.kastapi.annotations.*
import org.darchest.kastapi.ktor.utility.InMemoryFile
import org.darchest.kastapi.ktor.utility.Wrapper
import kotlin.text.toByteArray

@Routes("openApi")
@PackageName("api")
@RemoveWrappers(EmptyWrapper::class)
class OpenApiStore {

    class Item(val id: Int, val name: String)

    class ResponseTest(
        val message: String,
        val number: Int,
        val map: Map<String, Item>,
        val array: List<String>
    )

    @Get
    fun testResponse(): ResponseTest {
        TODO()
    }
}
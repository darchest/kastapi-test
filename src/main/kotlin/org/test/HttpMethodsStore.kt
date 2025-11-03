package org.test

import org.darchest.kastapi.annotations.*

/*
Можно принимать запросы любого Http метода
По умолчанию конечный url сегмент определяется наименованием функции, но можно указать его явно
объявив аргументом в аннотации Http метода. Например, @Get("customGet")
 */

@Routes("httpMethods")
@PackageName("api")
@RemoveWrappers(EmptyWrapper::class)
class HttpMethodsStore {

    @Delete
    fun delete(): String {
        return "OK"
    }

    @Get
    fun get(): String {
        return "OK"
    }

    @Head
    fun head(): String {
        return "OK"
    }

    @Options
    fun options(): String {
        return "OK"
    }

    @Patch
    fun patch(): String {
        return "OK"
    }

    @Post
    fun post(): String {
        return "OK"
    }

    @Put
    fun put(): String {
        return "OK"
    }

    @Get("customGet")
    fun getFn(): String {
        return "OK"
    }
}
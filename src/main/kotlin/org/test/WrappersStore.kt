package org.test

import org.darchest.kastapi.annotations.*
import org.darchest.kastapi.ktor.utility.InMemoryFile
import org.darchest.kastapi.ktor.utility.Wrapper

class EmptyWrapper : Wrapper {
    override suspend fun <T> wrap(block: suspend () -> T): T {
        val res = block()
        return res
    }
}

class LogWrapper : Wrapper {
    override suspend fun <T> wrap(block: suspend () -> T): T {
        println("START")
        val res = block()
        println("FINISH")
        return res
    }
}

class TimerWrapper : Wrapper {
    override suspend fun <T> wrap(block: suspend () -> T): T {
        val time = System.currentTimeMillis()
        val res = block()
        val dur = System.currentTimeMillis() - time
        println("Duration: $dur")
        return res
    }
}

/*
Обёртки могут быть добавлены на трёх уровнях: глобальном, Routes-классе и функции
Глобально обёртка указывается путём указания аргумента ksp org.darchest.kastapi.defaultWrappers см. build.gradle.kts
Список глобальных обёрток указывается через ';'
Routes-обёртка и обёртка функции указывается через аннотацию @AddWrappers
Каждая обёртка должна реализовывать интерфейс Wrapper
Для исключения обёртки более высокого уровня из конкретного Routes-класса или функции можно использовать
аннотацию @RemoveWrappers
 */

@Routes("wrappers")
@PackageName("api")
@AddWrappers(LogWrapper::class)
class WrappersStore {

    @Get
    @AddWrappers(TimerWrapper::class)
    fun summarization(): String {
        return "OK"
    }

    @Get
    @RemoveWrappers(LogWrapper::class)
    fun remove(): String {
        return "OK"
    }
}
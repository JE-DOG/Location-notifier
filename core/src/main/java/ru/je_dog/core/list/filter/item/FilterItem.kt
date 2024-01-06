package ru.je_dog.core.list.filter.item

interface FilterItem<FV,T> {

    var filterValue: FV

    fun execute(item: T): Boolean

    class Base<T>(
        private val returnValue: Boolean = true
    ): FilterItem<Any, T> {

        override var filterValue: Any = TODO("Nothing here")

        override fun execute(item: T): Boolean = returnValue

    }

}
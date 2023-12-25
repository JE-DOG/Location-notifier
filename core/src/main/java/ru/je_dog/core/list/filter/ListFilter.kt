package ru.je_dog.core.list.filter

import ru.je_dog.core.list.filter.item.FilterItem

interface ListFilter<T> {

    var filterItem: FilterItem<*,T>

    fun filter(list: List<T>): List<T>

}
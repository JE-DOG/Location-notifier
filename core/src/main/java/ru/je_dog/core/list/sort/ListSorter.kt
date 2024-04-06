package ru.je_dog.core.list.sort

interface ListSorter<T> {

    fun sort(list: List<T>): List<T>

}
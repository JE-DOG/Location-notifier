package ru.je_dog.core.list.sort

class AlphabeticallyListSorter<T>(
    private val getString: (T) -> String
): ListSorter<T> {

    override fun sort(list: List<T>): List<T> = list.sortedWith(
        compareBy(String.CASE_INSENSITIVE_ORDER, getString)
    )

}
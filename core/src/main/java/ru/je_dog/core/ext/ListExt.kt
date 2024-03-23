package ru.je_dog.core.ext

fun<T> List<T>.remove(item: T): List<T> {
    val result = toMutableList()

    result.remove(item)

    return result
}

fun<T> List<T>.add(item: T): List<T>{
    val result = toMutableList()
    result.add(item)

    return result
}

fun<T> List<T>.updateItem(
    item: T,
    findBy: (T) -> Boolean
): List<T> {
    val result = toMutableList()
    val index = result.indexOfFirst(findBy)

    if (index != -1){
        result[index] = item
    }
    return result
}
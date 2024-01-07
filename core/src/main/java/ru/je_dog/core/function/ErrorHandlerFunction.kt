package ru.je_dog.core.function

fun isRunWithoutException(action: () -> Unit): Boolean {
    return try {
        action()
        true
    }catch (_: Exception){
        false
    }
}
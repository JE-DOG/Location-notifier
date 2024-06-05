package ru.je_dog.core.feature.utills.ext

val Number.seconds: Long  get() {
    return toInt() * 1000L
}
package ru.je_dog.core.feature.common.di

interface DaggerDepsProvider<D: DaggerComponentDeps> {

    val deps: D

}
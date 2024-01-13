package ru.je_dog.core.feature.base.di

interface DaggerDepsProvider<D: DaggerComponentDeps> {

    val deps: D

}
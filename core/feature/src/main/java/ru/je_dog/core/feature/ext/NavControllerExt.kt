package ru.je_dog.core.feature.ext

import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController

fun<T> NavController.observeResult(key: String,initialValue: T) = currentBackStackEntry
    ?.savedStateHandle
    ?.getStateFlow(key,initialValue)

fun<T> NavController.observeResult(@StringRes key: Int,initialValue: T) = currentBackStackEntry
    ?.savedStateHandle
    ?.getStateFlow(Resources.getSystem().getString(key),initialValue)


fun<T> NavController.returnResult(key: String,result: T) = previousBackStackEntry
    ?.savedStateHandle
    ?.set(key, result)

fun<T> NavController.returnResult(@StringRes key: Int,result: T) = previousBackStackEntry
    ?.savedStateHandle
    ?.set(Resources.getSystem().getString(key), result)

fun<T> NavController.removeResult(key: String) = currentBackStackEntry
    ?.savedStateHandle
    ?.remove<T>(key)

fun<T> NavController.removeResult(@StringRes key: Int) = currentBackStackEntry
    ?.savedStateHandle
    ?.remove<T>(Resources.getSystem().getString(key))

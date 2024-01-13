package ru.je_dog.core.feature.ext

import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

fun<T> NavController.observeResult(key: String,initialValue: T) = currentBackStackEntry
    ?.savedStateHandle
    ?.getStateFlow(key,initialValue)

@Composable
fun<T> NavController.observeResult(@StringRes key: Int,initialValue: T) = currentBackStackEntry
    ?.savedStateHandle
    ?.getStateFlow(stringResource(key),initialValue)


fun<T> NavController.returnResult(key: String,result: T) = previousBackStackEntry
    ?.savedStateHandle
    ?.set(key, result)

@Composable
fun<T> NavController.returnResult(@StringRes key: Int,result: T) = previousBackStackEntry
    ?.savedStateHandle
    ?.set(stringResource(key), result)

fun<T> NavController.removeResult(key: String) = currentBackStackEntry
    ?.savedStateHandle
    ?.remove<T>(key)

@Composable
fun<T> NavController.removeResult(@StringRes key: Int) = currentBackStackEntry
    ?.savedStateHandle
    ?.remove<T>(stringResource(key))

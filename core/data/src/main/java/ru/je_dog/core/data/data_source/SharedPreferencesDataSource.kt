package ru.je_dog.core.data.data_source

import android.content.Context

abstract class SharedPreferencesDataSource(
    context: Context,
    name: String,
    mode: Int = Context.MODE_PRIVATE,
) {

    protected val sharedPreferences = context.getSharedPreferences(
        name,
        mode
    )

}
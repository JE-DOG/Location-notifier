package ru.je_dog.core.list.filter.item

import ru.je_dog.core.ext.isSubstringFor

class SubstringFilterItemDecorator<T>(
    private val getString: (T) -> String,
    private val filterItem: FilterItem<*, T>
): FilterItem<String, T> {

    override var filterValue: String = ""

    override fun execute(item: T): Boolean {

        val string = getString(item)

        return if (string isSubstringFor filterValue){
            filterItem.execute(item)
        }else {
            false
        }

    }

}
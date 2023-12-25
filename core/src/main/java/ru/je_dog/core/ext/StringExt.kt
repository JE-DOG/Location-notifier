package ru.je_dog.core.ext

infix fun CharSequence.isSubstringFor(charSequence: CharSequence): Boolean {

    var currentIndexSubString = 0

    if (this.isEmpty())
        return true

    charSequence.forEachIndexed { index, char ->

        val currentCharSubstring = this[currentIndexSubString]

        if (char == currentCharSubstring){

            currentIndexSubString++

            if (currentIndexSubString == this.length)
                return true

        }else if (currentIndexSubString != 0){
            return false
        }

    }

    return false
}
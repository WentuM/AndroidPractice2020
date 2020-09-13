package com.example.androidpractice2020

class ExpHeir : Experience(19, 179), SomeMethods {
    private var name: String = "Danil"
    var flag: Boolean = false
        set(value) {
            field = value
        }

    override fun max(number1: Int, number2: Int): String {
        return when {
            number1 < number2 -> ("Минимальное число - $number1")
            else -> ("Минимальное число - $number2")
        }
    }

    override fun stringUpper(str1 : String) : String {
        return(str1.toUpperCase())
    }

    override fun stringLower(str2 : String) : String {
        return (str2.toLowerCase())
    }
}
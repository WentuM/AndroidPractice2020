package com.example.androidpractice2020

open class Experience(var age: Int, var height: Int) {
    private var i: Int = 0
    init {
        var sum = age + height
    }
    var mail: String = ""
    private var password: String = ""
    fun mai(mail: String) {
        print(mail)
    }
    open fun max(number1: Int, number2: Int): String {
        return when {
            number1 > number2 -> ("Максимальное число - $number1")
            else -> ("Максимальное число - $number2")
        }
    }
    fun cycle() : String {
        var mass = emptyArray<String>()
        for (i in 0..10) {
            mass[i] = "$i"
        }
        if (mass[0] == "0")
            return "cool"
        return "not cool"
    }
}
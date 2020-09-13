package com.example.androidpractice2020

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var message1: TextView? = null
    private var message2: TextView? = null
    private var message3: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nameStudent = User("Danil", 19, "KFU")
        val email = Experience(19, 171).mai("stud@kpfu")
        val exp = Experience(19, 170)
        message1 = findViewById(R.id.setMessage)
        message1?.text = exp.max(1, 12)
        val str = ExpHeir()
        message2 = findViewById(R.id.setMessage2)
        message2?.text = str.stringUpper("android the best")
        val exp2 = Experience(19, 172)
//        message3 = findViewById(R.id.setMessage4)
//        message3?.text = exp2.cycle()
    }
}
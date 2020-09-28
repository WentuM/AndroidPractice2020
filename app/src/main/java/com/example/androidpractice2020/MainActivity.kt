package com.example.androidpractice2020

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle(R.string.app_registration)
        btn_registr.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("fName", et_Name.text.toString())
            intent.putExtra("fSurname", et_Surname.text.toString())
            intent.putExtra("fEmail", et_Email.text.toString())
            intent.putExtra("fGender", et_Gender.text.toString())
            startActivity(intent)
            finish()
        }
    }
}
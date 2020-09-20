package com.example.androidpractice2020

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        profile_image.setImageResource(R.drawable.first_picture)
        visible(false)
        btn_edit.setOnClickListener {
            visible(true)
        }
    }

    fun visible(flag: Boolean) {
        if (flag) {
            etName.visibility = View.VISIBLE
            etSurname.visibility = View.VISIBLE
            etEmail.visibility = View.VISIBLE
            etSyte.visibility = View.VISIBLE
            etGender.visibility = View.VISIBLE
            etNumberPhone.visibility = View.VISIBLE
        } else {
            etName.visibility = View.INVISIBLE
            etSurname.visibility = View.INVISIBLE
            etEmail.visibility = View.INVISIBLE
            etSyte.visibility = View.INVISIBLE
            etGender.visibility = View.INVISIBLE
            etNumberPhone.visibility = View.INVISIBLE
        }
    }
}
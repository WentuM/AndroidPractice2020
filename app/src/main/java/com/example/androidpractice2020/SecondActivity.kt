package com.example.androidpractice2020

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.net.Uri.parse
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.second_activity.*


class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        setContentView(R.layout.second_activity)

        if (intent.getStringExtra("img") != null) {
            val text = intent.getStringExtra(Intent.EXTRA_TEXT)
            val uri = parse(intent.getStringExtra("img"))
            message_text.text = text
            val imageStream =
                contentResolver.openInputStream(uri)
            val selectedImage = BitmapFactory.decodeStream(imageStream)
            image_first.setImageBitmap(selectedImage)
        } else {
            val text = intent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)
            val imageStream =
                text?.let { contentResolver.openInputStream(it) }
            val selectedImage = BitmapFactory.decodeStream(imageStream)
            image_first.setImageBitmap(selectedImage)
        }
    }
}
package com.example.androidpractice2020

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException


class MainActivity : AppCompatActivity() {
    private var imageView: ImageView? = null
    private val REQUEST_CODE_PICK_IMAGE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Настраиваем для нее обработчик нажатий OnClickListener:
        btn_picture.setOnClickListener { //Вызываем стандартную галерею для выбора изображения с помощью Intent.ACTION_PICK:
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            //Тип получаемых объектов - image:
            photoPickerIntent.type = "image/*"

            //Запускаем переход с ожиданием обратного результата в виде информации об изображении:
            startActivityForResult(photoPickerIntent, REQUEST_CODE_PICK_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, imageReturnedIntent: Intent?) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent)
        when (requestCode) {
            REQUEST_CODE_PICK_IMAGE -> if (resultCode == Activity.RESULT_OK) {
                try {
                    val imageUri = imageReturnedIntent?.data
                    val photoPickerIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra("img", imageUri.toString());
                        val text = "Важное сообщение"
                        putExtra(Intent.EXTRA_TEXT, text)
                        //в другом intent data=null
                        data = imageUri
                        type = "image/uri"
                    }
                    startActivity(photoPickerIntent)
                    //finish не могу вызвать :D
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }
            }
        }
    }
}
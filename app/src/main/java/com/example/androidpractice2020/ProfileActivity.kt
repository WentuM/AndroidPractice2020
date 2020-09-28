package com.example.androidpractice2020

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.android.synthetic.main.activity_profile.*
import java.io.FileNotFoundException

//TODO:
//1) сохранение текущей записи на элементе(View/Edit)
//2) работа с default toolbar (поменять цвет, изменить расположение кнопок) supportactionbar
//3) запрет на нажатие кнопки редактирования isEnabled = false
//4) показывание кнопок на toolbar только при нажатии на редактирование, после нажатие на одну
//   из кнопок, убирает просмотр этих кнопок invalidateOptionsMenu()
//5) спросить про предыдущую домашку на примере репозитория
//6) доступ к галереи телефона при нажатии на кнопку профиля
class ProfileActivity : AppCompatActivity() {
    //библиотека glide
    private var imageView: ImageView? = null
    private val pickImage = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setTitle(R.string.app_profile)
        profile_image.setImageResource(R.drawable.first_picture)
        etNameView.setTextColor(Color.BLACK)
        etSurnameView.setTextColor(Color.BLACK)
        etEmailView.setTextColor(Color.BLACK)
        etGenderView.setTextColor(Color.BLACK)
        etNameView.text = intent.getStringExtra("fName")
        etSurnameView.text = intent.getStringExtra("fSurname")
        etEmailView.text = intent.getStringExtra("fEmail")
        etGenderView.text = intent.getStringExtra("fGender")
        invisible(false)
        btn_edit.setOnClickListener {
            btn_edit.visibility = View.INVISIBLE
            setTitle(R.string.app_edit)
            editFirst(etNameView, etName)
            editFirst(etSurnameView, etSurname)
            editFirst(etEmailView, etEmail)
            editFirst(etGenderView, etGender)
        }
        btn_exit.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        //Связываемся с нашим ImageView:
        imageView = findViewById<View>(R.id.profile_image) as ImageView
        //Связываемся с нашей кнопкой Button:
        val PickImage =
            findViewById<View>(R.id.btn_photo) as Button
        //Настраиваем для нее обработчик нажатий OnClickListener:
        PickImage.setOnClickListener { //Вызываем стандартную галерею для выбора изображения с помощью Intent.ACTION_PICK:
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            //Тип получаемых объектов - image:
            photoPickerIntent.type = "image/*"
            //Запускаем переход с ожиданием обратного результата в виде информации об изображении:
            startActivityForResult(photoPickerIntent, pickImage)
        }
    }

    fun invisible(flag: Boolean) {
        setTitle(R.string.app_profile)
        etName.visibility = View.INVISIBLE
        etSurname.visibility = View.INVISIBLE
        etEmail.visibility = View.INVISIBLE
        etSyte.visibility = View.INVISIBLE
        etGender.visibility = View.INVISIBLE
        etNumberPhone.visibility = View.INVISIBLE
    }

    fun edit(etView: TextView, etEdit: EditText) {
        etView.text = etEdit.text.toString()
        etView.visibility = View.VISIBLE
        etEdit.visibility = View.INVISIBLE
    }

    fun editFirst(etView: TextView, etEdit: EditText) {
        etView.visibility = View.INVISIBLE
        etEdit.visibility = View.VISIBLE
    }

    fun cancel(etView: TextView, etEdit: EditText) {
        etView.visibility = View.VISIBLE
        etEdit.visibility = View.INVISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_action, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> {
                Toast.makeText(this, "Сохранено", Toast.LENGTH_SHORT).show()
                edit(etNameView, etName)
                edit(etSurnameView, etSurname)
                edit(etEmailView, etEmail)
                edit(etGenderView, etGender)
                btn_edit.visibility = View.VISIBLE
            }
            R.id.menu_cancel -> {
                Toast.makeText(this, "Отмена", Toast.LENGTH_SHORT).show()
                cancel(etNameView, etName)
                cancel(etSurnameView, etSurname)
                cancel(etEmailView, etEmail)
                cancel(etGenderView, etGender)
                btn_edit.visibility = View.VISIBLE
            }
        }
        return super.onOptionsItemSelected(item)
    }

//    Обрабатываем результат выбора в галерее:
    override fun onActivityResult(requestCode: Int, resultCode: Int, imageReturnedIntent: Intent?) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent)
        when (requestCode) {
            pickImage -> if (resultCode == Activity.RESULT_OK) {
                try {

                    //Получаем URI изображения, преобразуем его в Bitmap
                    //объект и отображаем в элементе ImageView нашего интерфейса:
                    val imageUri = imageReturnedIntent?.data
                    val imageStream =
                        contentResolver.openInputStream(imageUri!!)
                    val selectedImage = BitmapFactory.decodeStream(imageStream)
                    imageView!!.setImageBitmap(selectedImage)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }
            }
        }
    }
}
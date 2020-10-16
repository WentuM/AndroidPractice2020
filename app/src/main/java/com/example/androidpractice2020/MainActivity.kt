package com.example.androidpractice2020


import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*

//анимации переходов между фрагментами: свои или можно использовать андроидовские?
//accent_color - всё о нём

class MainActivity : AppCompatActivity() {
    private val manager = supportFragmentManager
    private lateinit var views: Array<View>
    val i: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        views = arrayOf(view_1, view_2, view_3, view_4, view_5)
        val animRotate = AnimationUtils.loadAnimation(this, R.anim.rotate)
        val fragment1 = FirstFragment()
        val fragment2 = SecondFragment()
        val fragment3 = ThirdFragment()
        val fragment4 = FourthFragment()
        val fragment5 = FifthFragment()

        view_1.setOnClickListener {
            showFragment(fragment1)
            it.startAnimation(animRotate)
            makeViewSelectedFalse(it)
        }
        view_2.setOnClickListener {
            showFragment(fragment2)
            it.startAnimation(animRotate)
            makeViewSelectedFalse(it)
        }
        view_3.setOnClickListener {
            showFragment(fragment3)
            it.startAnimation(animRotate)
            makeViewSelectedFalse(it)
        }
        view_4.setOnClickListener {
            showFragment(fragment4)
            it.startAnimation(animRotate)
            makeViewSelectedFalse(it)
        }
        view_5.setOnClickListener {
            showFragment(fragment5)
            it.startAnimation(animRotate)
            makeViewSelectedFalse(it)
        }
    }

    //функция для замены выбора фрагмента
    private fun showFragment(frag: Fragment) {
        val transaction = manager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        transaction.replace(R.id.frameLayout, frag)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    //функция для активирования одной кнопки(визуально) и деактивирования других кнопок
    private fun makeViewSelectedFalse(view: View) {
        for (i in 0..4) {
            views[i].isSelected = false
        }
        view.isSelected = true
    }
}
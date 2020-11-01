package com.example.androidpractice2020

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.androidpractice2020.fragments.FragmentCardView
import com.example.androidpractice2020.fragments.FragmentRecycleView
import com.example.androidpractice2020.fragments.FragmentText
import com.example.androidpractice2020.recyclerview.ListAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val manager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment1 = FragmentText()
        val fragment2 = FragmentRecycleView()
        val fragment3 = FragmentCardView()
        bottom_navigation.setOnNavigationItemSelectedListener {item ->
            when (item.itemId) {
                R.id.page_1 -> showFragment(fragment1)
                R.id.page_2 -> showFragment(fragment2)
                R.id.page_3 -> showFragment(fragment3)
            }
            true
        }
    }
    private fun showFragment(frag: Fragment) {
        if (frag != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.framelayout, frag)
            transaction.commit()
        }
    }
}
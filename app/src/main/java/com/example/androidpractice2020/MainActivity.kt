package com.example.androidpractice2020

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.androidpractice2020.fragments.*
import com.example.androidpractice2020.recyclerview.ListAdapter
import com.example.androidpractice2020.recyclerview.Note
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add.*

class MainActivity : AppCompatActivity(), NoteDialogFragment.NoticeDialogListener {
    private val manager = supportFragmentManager
    val fragment1 = FragmentText()
    val fragment2 = FragmentRecycleView()
    val fragment3 = FragmentCardView()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

    override fun onDialogPositiveClick(dialog: DialogFragment?) {
        val title = dialog?.dialog?.dialog_title?.text.toString()
        val description = dialog?.dialog?.dialog_description?.text.toString()
        val position = dialog?.dialog?.dialog_position?.text.toString()
        print(title)
        print(description)
        print(position)

        val newList = NoteRepository.repeat()

        if (position == "" || newList.size <= position.toInt())
            newList.add(Note(title, description, NoteRepository.makeId()))
        else if (title == "" || description == "") {
            onDialogNegativeClick(dialog)
        }
        else
            newList.add(position.toInt() - 1, Note(title, description, NoteRepository.makeId()))

        fragment2.updateList(newList)
    }

    override fun onDialogNegativeClick(dialog: DialogFragment?) {
        TODO("Not yet implemented")
    }
}
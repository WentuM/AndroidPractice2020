package com.example.androidpractice2020.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidpractice2020.R

class NoteListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_todolist, container, false)
    }

    interface OnSelectedButtonListener {
        fun onButtonSelected(buttonIndex: Int)
    }
}
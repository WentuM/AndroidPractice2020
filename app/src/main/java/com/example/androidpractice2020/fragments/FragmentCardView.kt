package com.example.androidpractice2020.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidpractice2020.R
import com.example.androidpractice2020.cardview.ModelAdapter
import com.example.androidpractice2020.cardview.Models
import kotlinx.android.synthetic.main.fragment_card_view.*

class FragmentCardView : Fragment() {
    var adapter: ModelAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_card_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ModelAdapter(Models.models)

        recycler2.adapter = adapter
    }
}
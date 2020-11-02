package com.example.androidpractice2020.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.androidpractice2020.R
import com.example.androidpractice2020.recyclerview.ListAdapter
import com.example.androidpractice2020.recyclerview.Note
import com.example.androidpractice2020.recyclerview.TouchHelper
import kotlinx.android.synthetic.main.fragment_recycleview.*


class FragmentRecycleView : Fragment() {
    private var adapter: ListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycleview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val itemDecorator =
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!)
        val list = recycler
        list.addItemDecoration(itemDecorator)
        adapter = ListAdapter(
            NoteRepository.arraylist
        ) { index: Int ->
            var tempArr = NoteRepository.repeat()
            tempArr.removeAt(index)
            adapter?.updateDataSource(tempArr)
        }

        recycler.adapter = adapter

        fab.setOnClickListener {
            val newNoteDialogFragment = NoteDialogFragment()
            newNoteDialogFragment.show(requireFragmentManager(), "note")
        }

        val callback = TouchHelper(adapter!!)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(recycler)

    }

    fun updateList(newList: ArrayList<Note>) {
        adapter?.updateDataSource(newList)
    }
}
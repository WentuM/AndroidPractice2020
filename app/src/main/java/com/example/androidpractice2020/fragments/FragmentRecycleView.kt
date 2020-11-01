package com.example.androidpractice2020.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice2020.R
import com.example.androidpractice2020.recyclerview.ExampleAdapter
import com.example.androidpractice2020.recyclerview.ListAdapter
import com.example.androidpractice2020.recyclerview.Note
import kotlinx.android.synthetic.main.dialog_add.*
import kotlinx.android.synthetic.main.dialog_add.view.*
import kotlinx.android.synthetic.main.fragment_recycleview.*
import kotlinx.android.synthetic.main.note_item.*


class FragmentRecycleView : Fragment() {
    private val mExampleList: ArrayList<Note>? = null
    private val mRecyclerView: RecyclerView? = null
    private val mAdapter: ExampleAdapter? = null
    private val mLayoutManager: RecyclerView.LayoutManager? = null
    private var buttonInsert: Button? = null
    private var buttonRemove: Button? = null
    private var editTextInsert: EditText? = null
    private var editTextRemove: EditText? = null

    //    var mAdapter = ListAdapter(NoteRepository().arraylist) {}
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
        list.adapter = ListAdapter(NoteRepository().arraylist) {
        }
        fab.setOnClickListener {
            val dialogview = LayoutInflater.from(context).inflate(R.layout.dialog_add, null)
            val builder = AlertDialog.Builder(context).setView(dialogview).setTitle("Добавить")
            val alert = builder.show()
            btn_add.setOnClickListener {
                alert.dismiss()
                val title = dialogview.dialog_title.text.toString()
                val description = dialogview.dialog_description.text.toString()
                val position = Integer.parseInt(dialog_position.dialog_position.text.toString())
                NoteRepository().arraylist.add(Note(title, description, position))
            }
        }
//        val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                return false
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                var position = viewHolder.adapterPosition
//                NoteRepository().arraylist.removeAt(position)
//                ListAdapter(NoteRepository().arraylist) {}.notifyDataSetChanged()
//            }
//        }
//        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallBack)
//        itemTouchHelper.attachToRecyclerView(recycler)
    }

//    fun insertItem(position: Int) {
//        mExampleList!!.add(
//            position, Note("New Item At Position$position", "This is Line 2")
//        )
//        mAdapter!!.notifyItemInserted(position)
//    }

    fun removeItem(position: Int) {
        mExampleList!!.removeAt(position)
        mAdapter!!.notifyItemRemoved(position)
    }

//    fun setButtons() {
//        btn_add.setOnClickListener {
//            val position = .text.toString().toInt()
//            insertItem(position)
//        }
//        delete.setOnClickListener {
//            val position = .text.toString().toInt()
//            removeItem(position)
//        }
//    }

}
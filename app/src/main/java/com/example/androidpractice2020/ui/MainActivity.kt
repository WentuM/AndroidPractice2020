package com.example.androidpractice2020.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpractice2020.*
import com.example.androidpractice2020.database.NoteViewModel
import com.example.androidpractice2020.database.NoteViewModelFactory
import com.example.androidpractice2020.database.NotesApplication
import com.example.androidpractice2020.fragment.NoteEditAddFragment
import com.example.androidpractice2020.fragment.NoteListFragment
import com.example.androidpractice2020.interfacecell.CellClickListener
import com.example.androidpractice2020.recycleview.NoteListAdapter
import kotlinx.android.synthetic.main.activity_note_details.*
import kotlinx.android.synthetic.main.activity_todolist.*

class MainActivity : AppCompatActivity(),
    NoteListFragment.OnSelectedButtonListener,
    CellClickListener {

    private val manager = supportFragmentManager
    private val newNoteActivityRequestCode = 1
//    private val viewModels = ViewModelProvider(this).get(NoteViewModel::class.java)
    private val noteViewModel: NoteViewModel by viewModels {
    NoteViewModelFactory((application as NotesApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentEditNote =
            NoteEditAddFragment()
        val fragmentListNote =
            NoteListFragment()

        val recyclerView = findViewById<RecyclerView>(R.id.list_adapter)
        val adapter =
            NoteListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        noteViewModel.allNotes.observe(owner = this) { notes ->
            notes.let { adapter.submitList(it) }
        }

        fab.setOnClickListener {
            showFragment(fragmentEditNote)
        }

        but_cancel.setOnClickListener {
            showFragment(fragmentListNote)
        }

        but_save.setOnClickListener {

        }
    }

    private fun showFragment(frag: Fragment) {
        val transaction = manager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        transaction.replace(R.id.mainFragment, frag)
        transaction.commit()
    }

    override fun onButtonSelected(buttonIndex: Int) {
        val fragmentManager = supportFragmentManager

        // Получаем ссылку на второй фрагмент по ID
//        val fragment2 = fragmentManager.findFragmentById(R.id.activity_note) as NoteEditAddFragment?
//        fragment2?.setDescription(buttonIndex)
    }

    override fun onCellClickListener() {
        Toast.makeText(this,"Cell clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onDeleteClickListener() {
        Toast.makeText(this,"Delete node:", Toast.LENGTH_SHORT).show()
    }
}
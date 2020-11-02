package com.example.androidpractice2020.fragments

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.androidpractice2020.R

class NoteDialogFragment : DialogFragment() {
    interface NoticeDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment?)
        fun onDialogNegativeClick(dialog: DialogFragment?)
    }

    // Use this instance of the interface to deliver action events
    var mListener: NoticeDialogListener? = null

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    override fun onAttach(activity: Context) {
        super.onAttach(activity)
        // Verify that the host activity implements the callback interface
        mListener = try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            activity as NoticeDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException(
                activity.toString()
                        + " must implement NoticeDialogListener"
            )
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder = android.app.AlertDialog.Builder(activity)
        var inflater = activity?.layoutInflater
        var dialogView = inflater?.inflate(R.layout.dialog_add, null)

        builder
            .setView(dialogView)
            .setNegativeButton("Отмена") { _, _ ->
                this.dialog?.cancel()
            }
            .setPositiveButton("Добавить") { _, _ ->
                mListener?.onDialogPositiveClick(this)
            }

        return builder.create()
    }
}
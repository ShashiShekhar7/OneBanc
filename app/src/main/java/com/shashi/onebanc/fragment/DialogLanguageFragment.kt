package com.shashi.onebanc.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.shashi.onebanc.R

class DialogLanguageFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater
        val view: View = inflater.inflate(R.layout.fragment_dialog_language, null)

        builder.setView(view)
            .setTitle("Change Language")
            .setNegativeButton("Close") { _, _ -> }
            .setPositiveButton("Change") { _, _ -> }

        val dialog:Dialog = builder.create()

        return dialog
    }
}
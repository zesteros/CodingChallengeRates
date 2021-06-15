package com.angelo.codingchallenge.presentation.core

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.angelo.codingchallenge.R

class ErrorDialogFragment(val code: Int?, private val message: String?) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
            AlertDialog.Builder(requireContext())
                .setTitle(code.toString())
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok)) { _,_ -> }
                .create()

    companion object {
        const val TAG = "ErrorDialogFragment"
    }
}
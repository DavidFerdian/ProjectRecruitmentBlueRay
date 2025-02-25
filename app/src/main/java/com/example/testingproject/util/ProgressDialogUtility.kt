package com.example.testingproject.util
import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.testingproject.R


object ProgressDialogUtility {

    lateinit var progressDialog: AlertDialog
    var isProgressDialogShowing = false

    fun showProgressDialog(context: Context) {
        if (!isProgressDialogShowing) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setCancelable(false) // Prevent user interaction during the process
            builder.setView(R.layout.layout_progress_dialog)

            progressDialog = builder.create()
            progressDialog?.show()
            isProgressDialogShowing = true // Set the flag to true once the dialog is shown
        }
    }

    fun dismissProgressDialog() {
        try {
            progressDialog?.dismiss()
            isProgressDialogShowing = false // Reset the flag after the dialog is dismissed
        } catch (error: Exception) {
            // Handle the exception if needed
        }
    }

}
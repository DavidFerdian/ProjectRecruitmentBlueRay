package com.example.testingproject.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast

object CopyToClipboardUtility {
    fun copyText(context: Context, textToCopy: CharSequence) {
        // Get the clipboard system service
        val clipboardObject: ClipboardManager = context.getSystemService(
            Context.CLIPBOARD_SERVICE
        ) as ClipboardManager

        val clip = ClipData.newPlainText("RANDOM UUID", textToCopy)
        clipboardObject.setPrimaryClip(clip)

        Toast.makeText(context, "Nomor Tersalin", Toast.LENGTH_SHORT).show()
    }
}
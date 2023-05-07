package com.example.vamosrachar

import android.content.Context
import android.content.Intent

class ShareManager (private val context: Context) {
    fun shareText(text: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, "Compartilhar com")
        context.startActivity(shareIntent)
    }
}
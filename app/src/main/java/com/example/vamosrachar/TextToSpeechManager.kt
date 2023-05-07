package com.example.vamosrachar

import android.content.Context
import android.speech.tts.TextToSpeech

class TextToSpeechManager (private val context: Context) {
    private var textToSpeech: TextToSpeech? = null

    fun speak(text: String) {
        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }
    }

    fun destroy() {
        textToSpeech?.stop()
        textToSpeech?.shutdown()
    }

}
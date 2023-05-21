package com.example.vamosrachar

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var textToSpeechManager : TextToSpeechManager
    private lateinit var shareManager : ShareManager

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textToSpeechManager = TextToSpeechManager(this)
        shareManager = ShareManager(this)

        val precoDividirEditText = findViewById<EditText>(R.id.precoDividir)
        val numeroPessoasEditText = findViewById<EditText>(R.id.numeroPessoas)
        val precoDivididoTextView = findViewById<TextView>(R.id.showPrecoDividido)
        val ttsBtn = findViewById<FloatingActionButton>(R.id.ttsBtn)
        val shareBtn = findViewById<FloatingActionButton>(R.id.shareBtn)

        precoDividirEditText.setSelection(0)
        numeroPessoasEditText.setSelection(0)

        precoDivididoTextView.text = "0.00"

        val precoDivididoUpdater = PrecoDivididoUpdater(precoDividirEditText, numeroPessoasEditText, precoDivididoTextView)
        precoDividirEditText.addTextChangedListener(precoDivididoUpdater)
        numeroPessoasEditText.addTextChangedListener(precoDivididoUpdater)

        ttsBtn.setOnClickListener {
            val precoDividido = precoDivididoTextView.text.toString()
            textToSpeechManager.speak(interactionMsg(precoDividido))
        }

        shareBtn.setOnClickListener {
            val precoDividido = precoDivididoTextView.text.toString()
            shareManager.shareText(interactionMsg(precoDividido))
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        textToSpeechManager.destroy()
    }

    fun interactionMsg(precoDividido: String): String {
        val interactionMsg = getString(R.string.interactionMsg)
        val cifrao = getString(R.string.cifrao)
        val fullMsg = interactionMsg + cifrao + precoDividido
        return fullMsg
    }
}
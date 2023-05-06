package com.example.vamosrachar

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var textToSpeech : TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val precoDividirEditText = findViewById<EditText>(R.id.precoDividir)
        val numeroPessoasEditText = findViewById<EditText>(R.id.numeroPessoas)
        val precoDivididoTextView = findViewById<TextView>(R.id.showPrecoDividido)
        val ttsBtn = findViewById<Button>(R.id.ttsBtn)
        val shareBtn = findViewById<Button>(R.id.shareBtn)

        precoDividirEditText.setSelection(0)
        numeroPessoasEditText.setSelection(0)

        precoDividirEditText.setText("0,00")
        numeroPessoasEditText.setText("1")
        precoDivididoTextView.text = "0,00"

        precoDividirEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                updateTextPrecoDividido(precoDivididoTextView, precoDividirEditText, numeroPessoasEditText)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // None
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // None
            }
        })

        numeroPessoasEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                updateTextPrecoDividido(precoDivididoTextView, precoDividirEditText, numeroPessoasEditText)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // None
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // None
            }
        })

        ttsBtn.setOnClickListener() {
            val precoDividido = precoDivididoTextView.text.toString()
            textToSpeech = TextToSpeech(this) { status ->
                if (status == TextToSpeech.SUCCESS) {
                    speak("O valor a ser pago por pessoa é de $precoDividido R$")
                }
            }
        }

        shareBtn.setOnClickListener() {
            val precoDividido = precoDivididoTextView.text.toString()
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "O valor a ser pago por pessoa é de $precoDividido R$")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, "Compartilhar com")
            startActivity(shareIntent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        textToSpeech.stop()
        textToSpeech.shutdown()
    }

    private fun updateTextPrecoDividido(precoDivididoTextView: TextView, precoDividirEditText: EditText, numeroPessoasEditText: EditText) {
        val precoDividir = precoDividirEditText.text.toString().toDoubleOrNull() ?: 0.00
        val numeroPessoas = numeroPessoasEditText.text.toString().toIntOrNull() ?: 1
        val precoDividido : Double
        if (numeroPessoas == 0) {
            return
        } else {
            precoDividido = precoDividir / numeroPessoas

        }

        val precoDivididoFormatted = String.format("%.2f", precoDividido)
        precoDivididoTextView.text = precoDivididoFormatted
        println("Preco a se dividir: $precoDividido")
        println("Numero de pessoas: $numeroPessoas")
        println("Preco dividido: $precoDividir")


    }

    private fun speak(text: String) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

}
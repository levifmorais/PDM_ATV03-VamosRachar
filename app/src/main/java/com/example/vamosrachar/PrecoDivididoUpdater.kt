package com.example.vamosrachar

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView

class PrecoDivididoUpdater (
    private val precoDividirEditText: EditText,
    private val numeroPessoasEditText: EditText,
    private val precoDivididoTextView: TextView
        ) :TextWatcher {

    override fun afterTextChanged(p0: Editable?) {
        updateTextPrecoDividido()
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        // None
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        // None
    }

    private fun updateTextPrecoDividido() {
        val precoDividir = precoDividirEditText.text.toString().toDoubleOrNull() ?: 0.00
        val numeroPessoas = numeroPessoasEditText.text.toString().toIntOrNull() ?: 1
        val precoDividido: Double
        if (numeroPessoas == 0) {
            precoDividido = 0.00
        } else {
            precoDividido = precoDividir / numeroPessoas
        }

        val precoDivididoFormatted = String.format("%.2f", precoDividido)
        precoDivididoTextView.text = precoDivididoFormatted
        Log.d("PrecoDivididoUpdater", "Preco a se dividir: $precoDividido")
        Log.d("PrecoDivididoUpdater", "Numero de pessoas: $numeroPessoas")
        Log.d("PrecoDivididoUpdater", "Preco dividido: $precoDividir")
    }

}
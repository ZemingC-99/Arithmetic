package com.cs501.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var resultText: TextView
    private lateinit var operationSpinner: Spinner
    private lateinit var firstNum: EditText
    private lateinit var secondNum: EditText
    private lateinit var calculateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultText = findViewById(R.id.resultText)
        operationSpinner = findViewById(R.id.operationSpinner)
        firstNum = findViewById(R.id.firstNum)
        secondNum = findViewById(R.id.secondNum)
        calculateButton = findViewById(R.id.calculateButton)
        calculateButton.setOnClickListener {
            calculate()
        }
    }

    private fun calculate() {
        val num1 = firstNum.text.toString().toDoubleOrNull() ?: return
        val num2 = secondNum.text.toString().toDoubleOrNull() ?: return

        if (num1 == null || num2 == null) {
            showError("Oops, please enter valid numbers.")
            return
        }

        val result = when (operationSpinner.selectedItem.toString()) {
            "Add" -> num1 + num2
            "Subtract" -> num1 - num2
            "Multiply" -> num1 * num2
            "Divide" -> {
                if (num2 == 0.0) {
                    showError("Oops, cannot divide by zero!")
                    return
                }
                num1 / num2
            }
            "Modulus" -> num1 % num2
            else -> 0.0
        }

        if (result > 1e8) {
            showError("Oops, Result is too large to display!")
            return
        }

        resultText.text = result.toString()
    }

    private fun showError(message: String) {
        Snackbar.make(
            findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
    }
}
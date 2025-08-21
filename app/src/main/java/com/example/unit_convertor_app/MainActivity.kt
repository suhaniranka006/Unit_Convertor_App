package com.example.unit_convertor_app

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * MAIN ACTIVITY: Unit Converter Application
 * STRATEGY PATTERN: Uses Converter interface for different conversion algorithms
 * POLYMORPHISM: Treats all converters uniformly through common interface
 * ENCAPSULATION: UI components are private and protected
 */
class MainActivity : AppCompatActivity() {

    // ENCAPSULATION: Private UI components
    private lateinit var convertorType: Spinner
    private lateinit var inputValue: EditText
    private lateinit var resultText: TextView
    private lateinit var btnConvert: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize UI components
        convertorType = findViewById(R.id.spinnerConvertorType)
        inputValue = findViewById(R.id.inputValue)
        resultText = findViewById(R.id.resultText)
        btnConvert = findViewById(R.id.btnConvert)

        // Setup spinner with conversion types
        val types = listOf("Length", "Weight", "Temperature")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, types)
        convertorType.adapter = adapter

        // Convert button click listener
        btnConvert.setOnClickListener {
            val value = inputValue.text.toString().toDoubleOrNull()
            val selectedType = convertorType.selectedItem.toString()

            if (value != null) {
                // STRATEGY PATTERN: Create appropriate converter based on selection
                val converter: Converter = when (selectedType) {
                    "Length" -> LengthConvertor()
                    "Weight" -> WeightConvertor()
                    "Temperature" -> TemperatureConvertor()
                    else -> LengthConvertor() // Default fallback
                }

                // POLYMORPHISM: Same method call for all converter types
                val result = converter.convert(value)
                resultText.text = "Converted Value: $result"
            } else {
                // Error handling for invalid input
                resultText.text = "Please enter a valid number"
            }
        }

        // Edge-to-edge window insets handling
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
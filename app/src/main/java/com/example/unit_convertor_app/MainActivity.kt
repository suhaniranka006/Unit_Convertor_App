/**
 * PACKAGE DECLARATION: Unique identifier for the app
 * WHY: Prevents naming conflicts with other apps
 * HOW: Reverse domain name convention (com.company.appname)
 */
package com.example.unit_convertor_app

// IMPORT STATEMENTS: Android framework classes we need to use
// WHY: Cannot use Android classes without importing them
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
 * MAIN ACTIVITY CLASS: Entry point of the application
 * INHERITANCE: Extends AppCompatActivity for Android lifecycle and features
 * WHY: AppCompatActivity provides modern Android features and backward compatibility
 */
class MainActivity : AppCompatActivity() {

    /**
     * UI COMPONENT DECLARATIONS with lateinit
     * lateinit: Promise to initialize before use (avoid null checks)
     * WHY: Cannot initialize in declaration because findViewById() needs onCreate()
     */
    private lateinit var convertorType: Spinner  // Dropdown for conversion type selection
    private lateinit var inputValue: EditText    // Input field for numeric value
    private lateinit var resultText: TextView    // Display area for conversion result
    private lateinit var btnConvert: Button      // Button to trigger conversion

    /**
     * onCreate(): Android lifecycle method called when activity is created
     * @param savedInstanceState: Saved state from previous instance (can be null)
     * WHY: Main initialization point for activity setup
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge display (modern Android UI)
        enableEdgeToEdge()

        // Set the XML layout file for this activity
        setContentView(R.layout.activity_main)

        /**
         * UI COMPONENT INITIALIZATION: Connect XML elements to code variables
         * HOW: findViewById() searches XML layout for elements with specified ID
         * WHY: Need reference to UI elements to control them programmatically
         */
        convertorType = findViewById(R.id.spinnerConvertorType)
        inputValue = findViewById(R.id.inputValue)
        resultText = findViewById(R.id.resultText)
        btnConvert = findViewById(R.id.btnConvert)

        /**
         * SPINNER SETUP: Configure dropdown with conversion types
         * WHY: Let user select what type of conversion they want
         */
        val types = listOf("Length", "Weight", "Temperature")  // Available conversion types

        // ArrayAdapter: Bridge between data (types) and Spinner view
        // Parameters: Context, Layout for each item, Data list
        val adapter = ArrayAdapter(
            this,                       // Context (current activity)
            android.R.layout.simple_spinner_item,  // Built-in layout for spinner items
            types                       // Data to display
        )

        // Set the adapter to spinner - tells spinner where to get data
        convertorType.adapter = adapter

        /**
         * CONVERT BUTTON CLICK LISTENER: Handle button click events
         * WHY: Execute code when user taps the convert button
         */
        btnConvert.setOnClickListener {
            /**
             * INPUT VALIDATION: Safely convert user input to number
             * toDoubleOrNull(): Returns null if input is not valid number (prevents crash)
             * WHY: Users might enter non-numeric values - need graceful handling
             */
            val value = inputValue.text.toString().toDoubleOrNull()

            // Get selected conversion type from spinner
            val selectedType = convertorType.selectedItem.toString()

            // Check if input is valid
            if (value != null) {
                /**
                 * STRATEGY PATTERN: Create appropriate converter based on user selection
                 * WHY: Different conversion algorithms encapsulated in separate classes
                 * HOW: when expression selects converter implementation
                 */
                val converter: Converter = when (selectedType) {
                    "Length" -> LengthConvertor()        // Length conversion strategy
                    "Weight" -> WeightConvertor()        // Weight conversion strategy
                    "Temperature" -> TemperatureConvertor() // Temperature conversion strategy
                    else -> LengthConvertor()            // Default fallback
                }

                /**
                 * POLYMORPHISM: Call convert() method - actual implementation depends on converter type
                 * WHY: Client code doesn't need to know about specific conversion algorithms
                 */
                val result = converter.convert(value)

                // Display result to user
                resultText.text = "Converted Value: $result"
            } else {
                /**
                 * ERROR HANDLING: User entered invalid input
                 * WHY: Guide user to correct their input instead of crashing
                 */
                resultText.text = "Please enter a valid number"
            }
        }

        /**
         * EDGE-TO-EDGE UI HANDLING: Make app content extend behind system bars
         * WHY: Modern Android design guideline - better user experience
         */
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            // Get dimensions of system bars (status bar + navigation bar)
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            // Apply padding to avoid content being hidden behind system bars
            v.setPadding(
                systemBars.left,    // Left padding = left system bar width
                systemBars.top,     // Top padding = status bar height
                systemBars.right,   // Right padding = right system bar width
                systemBars.bottom   // Bottom padding = navigation bar height
            )

            // Return the insets for proper event handling
            insets
        }
    }
}




//package com.example.unit_convertor_app
//
//import android.os.Bundle
//import android.widget.ArrayAdapter
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Spinner
//import android.widget.TextView
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//
///**
// * MAIN ACTIVITY: Unit Converter Application
// * STRATEGY PATTERN: Uses Converter interface for different conversion algorithms
// * POLYMORPHISM: Treats all converters uniformly through common interface
// * ENCAPSULATION: UI components are private and protected
// */
//class MainActivity : AppCompatActivity() {
//
//    // ENCAPSULATION: Private UI components
//    private lateinit var convertorType: Spinner
//    private lateinit var inputValue: EditText
//    private lateinit var resultText: TextView
//    private lateinit var btnConvert: Button
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//
//        // Initialize UI components
//        convertorType = findViewById(R.id.spinnerConvertorType)
//        inputValue = findViewById(R.id.inputValue)
//        resultText = findViewById(R.id.resultText)
//        btnConvert = findViewById(R.id.btnConvert)
//
//        // Setup spinner with conversion types
//        val types = listOf("Length", "Weight", "Temperature")
//        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, types)
//        convertorType.adapter = adapter
//
//        // Convert button click listener
//        btnConvert.setOnClickListener {
//            val value = inputValue.text.toString().toDoubleOrNull()
//            val selectedType = convertorType.selectedItem.toString()
//
//            if (value != null) {
//                // STRATEGY PATTERN: Create appropriate converter based on selection
//                val converter: Converter = when (selectedType) {
//                    "Length" -> LengthConvertor()
//                    "Weight" -> WeightConvertor()
//                    "Temperature" -> TemperatureConvertor()
//                    else -> LengthConvertor() // Default fallback
//                }
//
//                // POLYMORPHISM: Same method call for all converter types
//                val result = converter.convert(value)
//                resultText.text = "Converted Value: $result"
//            } else {
//                // Error handling for invalid input
//                resultText.text = "Please enter a valid number"
//            }
//        }
//
//        // Edge-to-edge window insets handling
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//    }
//}
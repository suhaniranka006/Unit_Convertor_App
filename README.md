# 🔄 Unit Converter App (Kotlin + OOPs)

A simple **Unit Converter Android App** built with **Kotlin** and **XML UI**.  
The app demonstrates **core OOPs concepts** like **Abstraction, Inheritance, and Polymorphism** in a practical way.  

---

## 🚀 How It Works

User selects conversion type from dropdown (Spinner).

Enters value in EditText.

On button click, app:

Picks correct Converter class using when expression.

Calls convert() function (Polymorphism).

Displays result in TextView.


## ✨ Features
- 📏 **Length Converter** (Meters → Kilometers)
- ⚖️ **Weight Converter** (Grams → Kilograms)
- 🌡️ **Temperature Converter** (Celsius → Fahrenheit)
- 🎨 Simple, clean UI using **XML Layouts**
- 🧑‍💻 Demonstrates **Abstraction + Strategy Pattern** in Kotlin

---

## 🛠️ Tech Stack
- **Language:** Kotlin  
- **UI:** XML LinearLayout
- **IDE:** Android Studio  
- **Concepts Used:**  
  - Abstract Class (`Converter`)  
  - Inheritance (`LengthConverter`, `WeightConverter`, `TemperatureConverter`)  
  - Polymorphism (Overriding `convert()` method)  
  - Strategy Pattern (Choosing conversion logic at runtime)  
  - `lateinit` properties for UI components  

---

## 📂 Project Structure

unit_convertor_app/

┣ 📂 java/com/example/unit_convertor_app

┃ ┣ Converter.kt # Abstract base class

┃    ┣ LengthConvertor # Length converter (meter → km)

┃    ┣ WeightConvertor # Weight converter (grams → kg)

┃    ┣ TemperatureConvertor # Temperature converter (Celsius → Fahrenheit)

┃ ┗ MainActivity.kt # UI logic + strategy pattern

┣ 📂 res/layout

┃ ┗ activity_main.xml # Main UI

┣ 📂 res/values

┃ ┗ strings.xml, colors.xml # Resources

┗ AndroidManifest.xml


---

## 📊 UML Diagram


classDiagram

    class Converter {
        <<abstract>>
        +convert(value: Double): Double
    }

    class LengthConvertor {
        +convert(value: Double): Double
    }

    class WeightConvertor {
        +convert(value: Double): Double
    }

    class TemperatureConvertor {
        +convert(value: Double): Double
    }

    class MainActivity {
        -convertorType: Spinner
        -inputValue: EditText
        -resultText: TextView
        -btnConvert: Button
        +onCreate()
    }

    Converter <|-- LengthConvertor
    Converter <|-- WeightConvertor
    Converter <|-- TemperatureConvertor
    MainActivity --> Converter
    
  


## 📌 Future Enhancements

🔢 Add more units (Area, Volume, Time, Currency)

🎤 Add voice input

🎨 Add advanced UI with Material Design

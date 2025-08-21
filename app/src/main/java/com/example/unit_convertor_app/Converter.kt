package com.example.unit_convertor_app

//abstract base class
abstract class Converter {
    abstract fun convert(value : Double) : Double
}


//length convertor -- meter ->kilometer
class LengthConvertor : Converter() {
    override fun convert(value: Double): Double {
        return value/1000  //meters to km
    }
}


//wieght convertor -- grams -> kilogram
class WeightConvertor : Converter() {
    override fun convert(value: Double): Double {
        return value/1000  //gram to kg
    }
}

//Temperature convertor -- celsius to fehrenhit
class TemperatureConvertor : Converter() {
    override fun convert(value: Double): Double {
        return (value * 9/5) + 32
    }
}
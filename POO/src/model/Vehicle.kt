package model

open class Vehicle(val model_car : String, val brand : String) {
    open fun information() {
        println("Brand: $brand \n Model: $model_car")
    }
}
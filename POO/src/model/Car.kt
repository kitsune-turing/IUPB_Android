package model

class Car (modelCar : String, brand : String, val door : Int) : Vehicle(modelCar, brand) {
    override fun information() {
        println("Car: {Brand: $brand, \n  Model: $model_car,  \n  Doors: $door}")
    }
}
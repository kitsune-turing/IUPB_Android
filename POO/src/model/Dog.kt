package model

class Dog(name : String) : Animal(name) {
    override fun sound() {
        println("Guauuu")
    }
}
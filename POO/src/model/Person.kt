package model

class Person(val name : String, val edad : Int = 0) {

    fun Welcome(){
        println("Welcome $name; your age is $edad")
    }
}
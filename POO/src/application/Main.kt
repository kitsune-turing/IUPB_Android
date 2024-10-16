package application

import model.*

fun main(){
    println("-----------------------------------------------")
    val personOne = Person("Anderson", 19)
    val personTwo = Person("Lucia", 100)
    personOne.Welcome()
    personTwo.Welcome()

    println("-----------------------------------------------")

    val rectangle = Rectangle(5.0, 3.0)
    println("El área del rectángulo es: ${rectangle.area()}")
    println("El perímetro del rectángulo es: ${rectangle.perimeter()}")

    println("-----------------------------------------------")

    val dog = Dog("Rex")
    println("The dog name is ${dog.name}")
    dog.sound()

    println("-----------------------------------------------")

    val car = Car("Toyota", "Corolla", 4)
    car.information()

    println("-----------------------------------------------")

    val studentOne = Student("Carlos", 22, 7.5)
    val studentTwo = Student("María", 19, 5.0)
    studentOne.state()
    studentTwo.state()
    println("-----------------------------------------------")
}
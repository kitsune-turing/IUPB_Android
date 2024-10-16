package model

open class Animal(val name : String) {
    open fun sound(){
        println("Animal Sound")
    }
}
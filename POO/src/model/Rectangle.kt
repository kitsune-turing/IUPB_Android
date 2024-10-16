package model

class Rectangle(val width: Double, val height: Double) {

    fun area(): Double {
        return width * height
    }

    fun perimeter(): Double {
        return 2 * (width + height)
    }
}
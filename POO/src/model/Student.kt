package model

class Student(val name: String, val age: Int, val avg: Double) {
    fun state() {
        if( avg >= 6)
            println("The student $name, with age $age, and content this avg $avg, approved")
        else
            println("The student $name, with age $age, not content this avg $avg, disapproved")
    }
}
package com.example.tasks.util
//for sorting purpose
sealed class Note_order(
    val ordertype : Ordertype
){
    class Title(ordertype : Ordertype):Note_order(ordertype)
    class Date(ordertype :Ordertype) :Note_order(ordertype)
    class Color(ordertype :Ordertype) :Note_order(ordertype)


    fun copy(ordertype : Ordertype): Note_order{
        return when(this){
            is Title ->Title(ordertype)
            is Date -> Date(ordertype)
            is Color -> Color(ordertype)
        }
    }

}
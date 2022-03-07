package com.example.tasks.util

sealed class Ordertype(){
    object  Ascending : Ordertype()
    object Descending :Ordertype()

}
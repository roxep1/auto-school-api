package com.bashkir.models.types

enum class Code {
    STUDENT{
        override fun toString(): String = "Ученик"
           },
    TEACHER{
        override fun toString(): String = "Учитель"
    }
}
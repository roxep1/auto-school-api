package com.bashkir.models.types

enum class Code {
    STUDENT,
    TEACHER,
    ADMIN,
    ACCOUNTANT;

    override fun toString(): String = name
}
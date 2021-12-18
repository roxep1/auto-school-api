package com.bashkir.models.types

enum class Code {
    STUDENT,
    TEACHER,
    ADMIN,
    ACCOUNTANT,
    HR;

    override fun toString(): String = name
}
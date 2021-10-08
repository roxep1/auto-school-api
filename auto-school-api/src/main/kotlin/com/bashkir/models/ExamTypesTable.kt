package com.bashkir.models

import com.bashkir.StringEntityClass
import com.bashkir.StringIdTable
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.id.EntityID

object ExamTypesTable : StringIdTable("examTypes", "examTypeId", 50)

class ExamTypes(id : EntityID<String>): Entity<String>(id){
    companion object : StringEntityClass<ExamTypes>(ExamTypesTable)

    fun toModel(): ExamTypesModel = ExamTypesModel(this)
}

@Serializable
data class ExamTypesModel(
    @Transient private val examType: ExamTypes? = null
){
    val type = examType?.id?.value
}
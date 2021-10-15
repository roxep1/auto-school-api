package com.bashkir.models

import com.bashkir.EntityWithModel
import com.bashkir.StringEntityClass
import com.bashkir.StringIdTable
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.id.EntityID

object ExamTypesTable : StringIdTable("examTypes", "examTypeId", 50)

class ExamTypes(id : EntityID<String>): Entity<String>(id), EntityWithModel<ExamTypes.Model>{
    companion object : StringEntityClass<ExamTypes>(ExamTypesTable)

    override fun toModel(): Model = Model(this)

    @Serializable
    data class Model(
        @Transient private val examType: ExamTypes? = null
    ){
        val type = examType!!.id.value
    }
}

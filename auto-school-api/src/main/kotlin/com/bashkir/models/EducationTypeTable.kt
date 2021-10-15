package com.bashkir.models

import com.bashkir.EntityWithModel
import com.bashkir.StringEntityClass
import com.bashkir.StringIdTable
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.id.EntityID

object EducationTypeTable: StringIdTable("educationtype", "educationtypename", 50)

class EducationType(id : EntityID<String>): Entity<String>(id), EntityWithModel<EducationType.Model>{
    companion object : StringEntityClass<EducationType>(EducationTypeTable)

    @Serializable
    data class Model(@Transient val model: EducationType? = null){
        val id = model!!.id.value
    }

    override fun toModel(): Model = Model(this)
}
package com.bashkir.models

import com.bashkir.EntityWithModel
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object EducationTable: IntIdTable("education", "educationid") {
    val speciality = varchar("speciality", 100)
    val institution = varchar("institution", 100)
    val periodOfStudy = varchar("periodofstudy", 30)
    val educationTypeName = reference("educationtypename", EducationTypeTable)
    val employee = reference("phonenumber", EmployeesTable)
}

class Education(id: EntityID<Int>): IntEntity(id), EntityWithModel<Education.Model>{
    companion object : IntEntityClass<Education>(EducationTable)

    var speciality by EducationTable.speciality
    var institution by EducationTable.institution
    var periodOfStudy by EducationTable.periodOfStudy
    var educationTypeName by EducationType referencedOn EducationTable.educationTypeName
    var employee by Employee referencedOn EducationTable.employee

    @Serializable
    data class Model(@Transient val model: Education? = null){

        val id = model!!.id.value
        val speciality = model!!.speciality
        val institution = model!!.institution
        val periodOfStudy = model!!.periodOfStudy
        val educationTypeName = model!!.educationTypeName.id.value
        val employee = model!!.employee.id.value
    }

    override fun toModel(): Model = Model(this)
}
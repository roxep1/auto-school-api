package com.bashkir.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.`java-time`.date
import java.time.LocalDate

object ResultsTable: IntIdTable("results", "resultId") {
    val phoneNumber = reference("people", PeopleTable)
    val examType = reference("examtypeid", ExamTypesTable)
    val date : Column<LocalDate> = date("date")
    val result: Column<String> = varchar("result", 18)
}

class Results(id: EntityID<Int>): IntEntity(id){
    companion object : IntEntityClass<Results>(ResultsTable)

    var phoneNumber by People referencedOn ResultsTable.phoneNumber
    var examType by ExamTypes referencedOn ResultsTable.examType
    var date by ResultsTable.date
    var result by ResultsTable.result

    fun toModel(): ResultModel = ResultModel(this)
}

@Serializable
data class ResultModel constructor(@Transient private val results: Results? = null){

    val id = results?.id?.value
    val phoneNumber = results?.phoneNumber?.id?.value
    val examType = results?.examType?.id?.value
    val date = results?.date?.toString()
    val result = results?.result
}
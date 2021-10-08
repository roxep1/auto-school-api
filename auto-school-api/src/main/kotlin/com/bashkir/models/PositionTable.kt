package com.bashkir.models

import com.bashkir.PGEnum
import com.bashkir.StringEntityClass
import com.bashkir.StringIdTable
import com.bashkir.models.types.Code
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Column

object PositionTable : StringIdTable("position", "positionName", 18) {
    val code: Column<Code> =
        customEnumeration("codename", "Code", { value -> Code.valueOf(value as String) }, { PGEnum("Code", it) })
}

class Position(id: EntityID<String>): Entity<String>(id){
    companion object: StringEntityClass<Position>(PositionTable)

    var code by PositionTable.code

    fun toModel(): PositionModel = PositionModel(this)
}

@Serializable
data class PositionModel(@Transient private val pos: Position? = null) {
    val position = pos?.id?.value
    val code = pos?.code
}

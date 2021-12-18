package com.bashkir.models

import com.bashkir.EntityWithModel
import com.bashkir.StringEntityClass
import com.bashkir.StringIdTable
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.id.EntityID

object PositionTable : StringIdTable("position", "positionname", 18)

class Position(id: EntityID<String>): Entity<String>(id), EntityWithModel<Position.Model>{
    companion object: StringEntityClass<Position>(PositionTable)


    override fun toModel(): Model = Model(this)

    @Serializable
    data class Model(@Transient private val pos: Position? = null) {
        val position = pos!!.id.value
    }
}
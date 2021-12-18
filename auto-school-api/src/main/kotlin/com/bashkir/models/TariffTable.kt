package com.bashkir.models

import com.bashkir.EntityWithModel
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object TariffTable: IntIdTable("tariffs", "tariffid") {
    val cost = float("cost")
    val name = text("name")
}

class Tariff(id: EntityID<Int>) : IntEntity(id), EntityWithModel<Tariff.Model>{
    companion object: IntEntityClass<Tariff>(TariffTable)

    var cost by TariffTable.cost
    var name by TariffTable.name


    @Serializable
    data class Model(@Transient val model: Tariff? = null) {
        val id = model!!.id.value
        val cost = model!!.cost
        val name = model!!.name
    }

    override fun toModel(): Model = Model(this)
}
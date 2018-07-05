package ffc.entity

import ffc.entity.gson.parseTo
import ffc.entity.gson.toJson
import me.piruin.geok.geometry.Point
import org.amshove.kluent.`should equal`
import org.junit.Test

class HouseTest {

    val house = House().apply {
        identity = ThaiHouseholdId("10125501411")
        link = Link(System.JHICS, "hid" to "100234")
        no = "302/21"
        road = "รังสิต-นครนายก"
        location = Point(14.077196, 100.5995609)
    }

    @Test
    fun toJson() {
        println(house.toJson())
    }

    @Test
    fun fromJson() {

        val json = """
            {"identity":{"type":"thailand-household-id","id":"10125501411"},"people":[],"link":{"isSynced":true,"lastSync":"2018-07-05T11:53:59.999+07:00","system":"JHICS","keys":{"hid":"100234"}},"no":"302/21","road":"รังสิต-นครนายก","location":{"type":"Point","coordinates":[100.5995609,14.077196]},"id":"4c9612f81718422fa11556c4f4b0c869","type":"House","timestamp":"2018-07-05T11:53:59.912+07:00"}
        """.trimIndent()

        val house = json.parseTo<House>()
        house.no `should equal` "302/21"
    }
}

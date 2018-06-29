package ffc.entity

import ffc.entity.gson.toJson
import me.piruin.geok.geometry.Point
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
}

package ffc.entity

import com.gregwoodfill.assert.`should equal json`
import ffc.entity.gson.parseTo
import ffc.entity.gson.toJson
import me.piruin.geok.geometry.Point
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should equal`
import org.joda.time.DateTime
import org.junit.Ignore
import org.junit.Test

class HouseTest {

    val house = House("123f678f90c").update(DateTime.parse("2018-06-25T14:09:07.815+07:00")) {
        identity = ThaiHouseholdId("10125501411")
        link = Link(System.JHICS, "hid" to "100234", lastSync = timestamp)
        no = "302/21"
        road = "รังสิต-นครนายก"
        location = Point(14.077196, 100.5995609)
        haveChronic = true
    }

    @Test
    fun name() {
        house.haveChronic `should be` true

        val healtyPerson = Person()
        house.people.add(healtyPerson)

        house.haveChronic `should be` false
    }

    @Ignore
    @Test
    fun print() {
        println(house.toJson())
    }

    @Test
    fun toJson() {
        house.toJson() `should equal json` resourceFile("House.json")
    }

    @Test
    fun fromJson() {
        val house = resourceFile("House.json").parseTo<House>()

        with(house) {
            identity `should equal` ThaiHouseholdId("10125501411")
            no `should equal` "302/21"
            road `should equal` "รังสิต-นครนายก"
            location `should equal` Point(14.077196, 100.5995609)
            link `should equal` Link(System.JHICS, "hid" to "100234")
        }
    }

}

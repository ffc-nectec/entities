package ffc.entity

import org.amshove.kluent.`should be`
import org.amshove.kluent.`should equal`
import org.amshove.kluent.`should not equal`
import org.joda.time.LocalDate
import org.junit.Test

class PersonTest {

    val person = Person().apply {
        identities.add(ThaiCitizenId("1154801544875"))
        prename = "นาย"
        firstname = "พิรุณ"

        lastname = "พานิชผล"
        birthDate = LocalDate.now().minusYears(25)
        link = Link(System.                  JHICS, "pid" to "1234567", "cid" to "11014578451234")
    }

    @Test
    fun timeStamp() {
        val timestampOnCreate = person.timestamp

        Thread.sleep(500)
        person.update<Person> {
            link = Link(System.JHICS, "pid" to "1234567")
        }

        person.timestamp `should not equal` timestampOnCreate
    }

    @Test
    fun age() {
        person.age `should be` 25
    }

    @Test
    fun name() {
        person.name `should equal` "นายพิรุณ พานิชผล"
    }

    @Test
    fun toJson() {
        println(person.toJson())
    }

    @Test
    fun fromJson() {
        val person = """
            {"identities":[{"type":"thailand-citizen-id","id":"1154801544875"}],"prename":"นาย","firstname":"พิรุณ","lastname":"พานิชผล","chronics":[],"sex":"UNKNOWN","birthDate":"1993-06-25","link":{"system":"JHICS","keys":{"pid":"1234567","cid":"11014578451234"}},"timestamp":"2018-06-25T11:35:24.602","_sync":false,"_id":"2731e839-128b-4dc3-8dd5-0f967e6143dd"}
        """.parseTo<Person>()

        with(person) {
            link!!.keys["pid"] `should equal` "1234567"
            link!!.keys["cid"]  `should equal` "11014578451234"
        }
    }

    @Test
    fun equal() {
        person `should equal` Person(person.id)
    }

    @Test
    fun isTempId() {
        person.isTempId `should be` true
    }

    @Test
    fun copy() {
        val copyPerson = person.copy<Person>("piruin")

        with(copyPerson) {
            id `should be` "piruin"
            name `should equal` "นายพิรุณ พานิชผล"
            person.age `should be` 25

        }
    }
}

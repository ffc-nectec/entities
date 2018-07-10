package ffc.entity

import com.gregwoodfill.assert.`should equal json`
import ffc.entity.gson.parseTo
import ffc.entity.gson.toJson
import ffc.entity.util.generateTempId
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should contain`
import org.amshove.kluent.`should equal`
import org.amshove.kluent.`should not contain`
import org.amshove.kluent.`should not equal`
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.junit.Ignore
import org.junit.Test

class PersonTest {

    val person = Person("e079e175c75a44f180e8eaeb").update<Person>(DateTime.parse("2018-06-25T14:09:07.815+07:00")) {
        identities.add(ThaiCitizenId("1154801544875"))
        prename = "นาย"
        firstname = "พิรุณ"
        lastname = "พานิชผล"
        birthDate = LocalDate.parse("1993-06-29")
        link = Link(System.JHICS, "pid" to "1234567", "cid" to "11014578451234",
                lastSync = DateTime.parse("2018-06-25T14:09:07.815+07:00"))
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

    @Ignore
    @Test
    fun print() {
        println(person.toJson())
    }

    @Test
    fun jsonShouldNotContainBundleValue() {
        person.bundle.put("say", "Hello World")

        val json = person.toJson()

        json `should not contain` "Hello World"
    }

    @Test
    fun toJson() {
        person.toJson() `should equal json` resourceFile("Person.json")
    }

    @Test
    fun fromJson() {
        val person = resourceFile("Person.json").parseTo<Person>()

        with(person) {
            identities `should contain` ThaiCitizenId("1154801544875")
            prename `should equal` "นาย"
            firstname `should equal` "พิรุณ"
            lastname `should equal` "พานิชผล"
            birthDate `should equal` LocalDate.parse("1993-06-29")
            link!!.keys["pid"] `should equal` "1234567"
            link!!.keys["cid"] `should equal` "11014578451234"
            link!!.isSynced `should be` true
        }
    }

    @Test
    fun equal() {
        person `should equal` Person(person.id)
    }

    @Test
    fun isTempId() {
        val person = person.copy<Person>(generateTempId())

        person.isTempId `should be` true
    }

    @Test
    fun copy() {
        val copyPerson = person.copy<Person>("piruin")

        with(copyPerson) {
            id `should be` "piruin"
            name `should equal` "นายพิรุณ พานิชผล"
            age `should be` 25
        }
    }
}

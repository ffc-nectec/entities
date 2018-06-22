package ffc.entity

import org.amshove.kluent.`should be`
import org.amshove.kluent.`should equal`
import org.amshove.kluent.`should not equal`
import org.joda.time.LocalDate
import org.junit.Test

class PersonTest {

    val person = Person {
        identities.add(ThaiCitizenId("1154801544875"))
        prename = "นาย"
        firstname = "พิรุณ"
        lastname = "พานิชผล"
        birthDate = LocalDate.now().minusYears(25)
    }

    @Test
    fun timeStamp() {
        val timestampOnCreate = person.timestamp

        Thread.sleep(500)
        person.update<Person> {
            houseId = 123456
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
    fun equal() {
        person `should equal` Person(person.id)
    }
}

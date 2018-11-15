package ffc.entity

import com.gregwoodfill.assert.`should equal json`
import ffc.entity.gson.parseTo
import ffc.entity.gson.toJson
import ffc.entity.healthcare.Icd10
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

    val person = Person("e079e175c75a44f180e8eaeb").update(DateTime.parse("2018-06-25T14:09:07.815+07:00")) {
        identities.add(ThaiCitizenId("1154801544875"))
        prename = "นาย"
        firstname = "พิรุณ"
        lastname = "พานิชผล"
        birthDate = LocalDate.parse("1993-06-29")
        avatarUrl = "https://avatars3.githubusercontent.com/u/783403?s=460&v=4"
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
        val person = person.copy(generateTempId())

        person.isTempId `should be` true
    }

    @Test
    fun copy() {
        val copyPerson = person.copy("piruin")

        with(copyPerson) {
            id `should be` "piruin"
            name `should equal` "นายพิรุณ พานิชผล"
            age `should be` 25
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidAvatarUrl() {
        person.avatarUrl = "InvlaidUrl.png"
    }

    @Test
    fun deadByCausesOfDeath() {
        person.death = Person.Death(LocalDate.now(), Icd10("Hypertension", "I15.9"))

        person.isDead `should be` true
    }

    @Test
    fun deathWithOnlyDate() {
        person.death = Person.Death(LocalDate.parse("2018-11-14"))

        with(person) {
            isDead `should be` true
            age `should be` 25
        }
    }
}

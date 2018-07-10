package ffc.entity

import com.gregwoodfill.assert.`should equal json`
import ffc.entity.gson.toJson
import org.amshove.kluent.`should be equal to`
import org.joda.time.DateTime
import org.junit.Ignore
import org.junit.Test

class OrganizationTest {

    val org = Organization("7e226a182d1448c69b308fb4").update<Organization>(DateTime.parse("2018-07-10T12:30:00.000+07:00")) {
        name = "รพ.สต. เนคเทค"
        users.add(User("ad030c").update(DateTime(timestamp)) {
            name = "blast"
            password = "aaddddres"
            role = User.Role.USER
        })
        users.add(User("123456").update(timestamp) {
            name = "pcu100214.airsync"
            password = "a1e6-4f63e8295160"
            role = User.Role.ORG
        })
        link = Link(System.JHICS, "pcucode" to 100214, lastSync = timestamp)
    }

    @Test
    fun toJson() {
        org.toJson() `should equal json` resourceFile("Organization.json")
    }

    @Ignore
    @Test
    fun print() {
        println(org.toJson())
    }

    @Test
    fun extendVariable() {
        org.apply { lastKnownIp = "127.0.0.1" }

        org.lastKnownIp `should be equal to` "127.0.0.1"
    }

    var Organization.lastKnownIp: String
        get() = bundle["ip"] as String
        set(value) {
            bundle.put("ip", value)
        }
}

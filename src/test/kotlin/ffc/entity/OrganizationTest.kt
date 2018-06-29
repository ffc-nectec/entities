package ffc.entity

import ffc.entity.gson.toJson
import org.amshove.kluent.`should be equal to`
import org.junit.Test

class OrganizationTest {

    val org = Organization().apply {
        name = "รพ.สต. เนคเทค"
        users.add(User().apply {
            name = "blast"
            password = "aaddddres"
            role = User.Role.USER
        })
        users.add(User("123456").apply {
            name = "pcu100214.airsync"
            password = "a1e6-4f63e8295160"
            role = User.Role.ORG
        })
        link = Link(System.JHICS, "pcucode" to 100214)
    }

    @Test
    fun toJson() {
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

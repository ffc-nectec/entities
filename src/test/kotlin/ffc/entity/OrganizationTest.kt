package ffc.entity

import org.amshove.kluent.`should be equal to`
import org.junit.Test

class OrganizationTest {

    @Test
    fun extendVariable() {
        val org = Organization().apply {
            lastKnownIp = "127.0.0.1"
        }

        org.lastKnownIp `should be equal to` "127.0.0.1"
    }

    var Organization.lastKnownIp: String
        get() = bundle["ip"] as String
        set(value) {
            bundle.put("ip", value)
        }
}

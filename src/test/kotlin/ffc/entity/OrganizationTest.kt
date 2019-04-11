/*
 * Copyright 2019 NECTEC
 *   National Electronics and Computer Technology Center, Thailand
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ffc.entity

import com.gregwoodfill.assert.`should equal json`
import ffc.entity.gson.toJson
import me.piruin.geok.geometry.Point
import org.amshove.kluent.`should be equal to`
import org.joda.time.DateTime
import org.junit.Ignore
import org.junit.Test

class OrganizationTest {

    val org = Organization("7e226a182d1448c69b308fb4").update(DateTime.parse("2018-07-10T12:30:00.000+07:00")) {
        name = "รพสต-เนคเทค"
        displayName = "รพ.สต. เนคเทค"
        users.add(User("ad030c").update(DateTime(timestamp)) {
            name = "blast"
            password = "aaddddres"
            role = User.Role.USER
            roles.add(User.Role.PROVIDER)
            roles.add(User.Role.SURVEYOR)
        })
        users.add(User("123456").update(timestamp) {
            name = "pcu100214.airsync"
            password = "a1e6-4f63e8295160"
            role = User.Role.ORG
            roles.add(User.Role.ADMIN)
        })
        tel = "02-564-6900"
        address = "อุธยานวิทยาศาสตร์ ปทุมธานี ประเทศไทย"
        location = Point(14.077065, 100.601616)
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

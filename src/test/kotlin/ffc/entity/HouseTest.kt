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
import ffc.entity.gson.parseTo
import ffc.entity.gson.toJson
import ffc.entity.place.House
import me.piruin.geok.geometry.Point
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should contain`
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

    @Test
    fun avatarUrlFromImagesList() {
        val firstImg = "https://cdn.pixabay.com/photo/2014/09/30/09/03/thailand-466936_960_720.jpg"
        house.imagesUrl.add(firstImg)

        house.avatarUrl `should equal` firstImg
    }

    @Test
    fun avatarUrlFromField() {
        val firstImg = "https://cdn.pixabay.com/photo/2014/09/30/09/03/thailand-466936_960_720.jpg"
        house.avatarUrl = firstImg

        house.avatarUrl `should equal` firstImg
        house.imagesUrl `should contain` firstImg
    }
}

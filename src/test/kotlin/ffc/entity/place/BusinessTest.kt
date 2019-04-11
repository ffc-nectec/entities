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

package ffc.entity.place

import ffc.entity.Link
import ffc.entity.Place
import ffc.entity.System
import ffc.entity.gson.parseTo
import ffc.entity.gson.toJson
import me.piruin.geok.geometry.Point
import org.amshove.kluent.`should equal`
import org.junit.Test

class BusinessTest {

    @Test
    fun businessFromJson() {
        val jsonBusiness = Business().apply {
            name = "ร้านอาหาร กินจุ"
            businessType = "ร้านอาหารริมทาง"
            location = Point(13.0, 100.3)
            no = "117/8"
            road = "ติวานนท์"
            villageName = "หมู่บ้านสายสิน"

            link = Link(System.JHICS)
            link?.keys?.put("pcucode", 1996)
            link?.keys?.put("villageid", 13)
            link?.keys?.put("businessno", 5555)
        }.toJson()

        val business = jsonBusiness.parseTo<Place>()

        (business as Business).businessType `should equal` "ร้านอาหารริมทาง"
    }
}

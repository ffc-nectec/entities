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

import ffc.entity.gson.toJson
import ffc.entity.place.Business
import me.piruin.geok.geometry.Point
import org.junit.Test

class VillageTest {
    val foodShop = Business().apply {
        name = "ร้านอาหาร กินจุ"
        businessType = "ร้านอาหารริมทาง"
        location = Point(13.0, 100.3)
        no = "117/8"
    }

    val place = Place().apply {
        villageName = "TTT"
    }

    val businsess = Business().apply {
        name = "บ้านเช่า นายนามี คมคนมา"
        businessType = "อาคารปล่อยเช่า"
        location = Point(13.009, 100.3453)
    }
    val nectecVillage = Village().apply {
        name = "หมู่บ้าน Nectec"
        link = Link(System.JHICS)
        link!!.keys["villcode"] = "13020200"
        link!!.keys["pcucode"] = "01092"

        places.add(place)
        places.add(foodShop)
        places.add(businsess)
    }
    val catVillage = Village().apply {
        name = "หมู่บ้าน Cat"
        places.add(foodShop)
        places.add(businsess)
    }
    val rabbitVillage = Village().apply {
        name = "หมู่บ้าน Rabbit"
        places.add(foodShop)
        places.add(businsess)
    }

    @Test
    fun toJson() {
        println(listOf(nectecVillage, catVillage, rabbitVillage).toJson())
    }
}

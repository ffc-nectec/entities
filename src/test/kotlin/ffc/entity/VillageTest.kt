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

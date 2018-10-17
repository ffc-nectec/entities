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

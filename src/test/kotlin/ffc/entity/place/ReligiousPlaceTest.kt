package ffc.entity.place

import ffc.entity.Link
import ffc.entity.Place
import ffc.entity.System
import ffc.entity.gson.parseTo
import ffc.entity.gson.toJson
import me.piruin.geok.geometry.Point
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should equal`
import org.junit.Test

class ReligiousPlaceTest {
    @Test
    fun templeFromJson() {
        val templeJson = ReligiousPlace().apply {
            name = "วัดสมใจ"
            religion = Religion.Buddhism

            location = Point(13.0, 100.3)
            no = "117/8"
            road = "ติวานนท์"
            villageName = "หมู่บ้านสายสิน"

            link = Link(System.JHICS)
            link?.keys?.put("pcucode", 1996)
            link?.keys?.put("villageid", 13)
            link?.keys?.put("businessno", 5555)
        }.toJson()

        val temple = templeJson.parseTo<Place>()
        temple as ReligiousPlace

        temple.name `should be equal to` "วัดสมใจ"
        temple.religion `should equal` Religion.Buddhism
    }
}

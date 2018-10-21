package ffc.entity.place

import ffc.entity.Link
import ffc.entity.Place
import ffc.entity.System
import ffc.entity.gson.parseTo
import ffc.entity.gson.toJson
import me.piruin.geok.geometry.Point
import org.amshove.kluent.`should equal`
import org.junit.Test

class SchoolTest {
    @Test
    fun schoolFromJson() {
        val jsonSchool = School().apply {
            name = "โรงเรียนอนุบาลหมีกินไผ่"
            educationLevel = Education.PreKindergarten
            isPrivate = true
            depend = "กรมส่งเสริม"

            location = Point(13.0, 100.3)
            no = "117/8"
            road = "ติวานนท์"
            villageName = "หมู่บ้านสายสิน"

            link = Link(System.JHICS)
            link?.keys?.put("pcucode", 1996)
            link?.keys?.put("villageid", 13)
            link?.keys?.put("businessno", 5555)
        }.toJson()

        val school = jsonSchool.parseTo<Place>()

        (school as School).depend `should equal` "กรมส่งเสริม"
    }
}

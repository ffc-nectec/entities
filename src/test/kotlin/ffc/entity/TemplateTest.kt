package ffc.entity

import com.gregwoodfill.assert.`should equal json`
import ffc.entity.gson.toJson
import org.junit.Test

class TemplateTest {

    @Test
    fun toJson() {
        val template = Template("ผู้ป่วยอาการปกติ ช่วยเหลือตัวเองได้", "HealthCareService.syntom")

        println(template.toJson())
        template.toJson() `should equal json` """
            {
                "value" : "ผู้ป่วยอาการปกติ ช่วยเหลือตัวเองได้",
                "field" : "HealthCareService.syntom"
            }""".trimIndent()
    }

    @Test(expected = IllegalArgumentException::class)
    fun InvalidFieldPattern() {
        Template("ผู้ป่วยอาการปกติ ช่วยเหลือตัวเองได้", "syntom")
    }
}

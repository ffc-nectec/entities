package ffc.entity

import com.gregwoodfill.assert.`should equal json`
import ffc.entity.gson.parseTo
import ffc.entity.gson.toJson
import org.amshove.kluent.`should equal`
import org.amshove.kluent.`should not equal`
import org.junit.Test

class IdentityTest {

    @Test
    fun thaiCitizenIdEquals() {
        ThaiCitizenId("1102304324006") `should equal` ThaiCitizenId("1102304324006")
        ThaiCitizenId("1102304324006") `should not equal` ThaiCitizenId("0")
        ThaiCitizenId("1102304324006") `should not equal` ThaiHouseholdId("1102304324006")
    }

    @Test
    fun thaiHouseholdIdEquals() {
        ThaiHouseholdId("10245003328") `should equal` ThaiHouseholdId("10245003328")
        ThaiHouseholdId("10245003328") `should not equal` ThaiHouseholdId("01")
        ThaiHouseholdId("10245003328") `should not equal` ThaiCitizenId("10245003328")
    }

    @Test
    fun thaiCitizenIdParseFromJson() {
        val thaiCitizenIdJson = """
{
  "type": "thailand-citizen-id",
  "id": "1102304324006"
}
        """.trimIndent()

        val thaiCitizenId = thaiCitizenIdJson.parseTo<ThaiCitizenId>()

        thaiCitizenId.id `should equal` "1102304324006"
    }

    @Test
    fun thaiHouseholdIdParseFromJson() {
        val houseCitizenIdJson = """
{
  "type": "thailand-household-id",
  "id": "10245003328"
}
        """.trimIndent()

        val houseCitizenId = houseCitizenIdJson.parseTo<ThaiCitizenId>()

        houseCitizenId.id `should equal` "10245003328"
    }

    @Test
    fun thaiCitizenIdJsonType() {
        ThaiCitizenId("1102304324006").toJson() `should equal json` """
{
  "type": "thailand-citizen-id",
  "id": "1102304324006"
}
"""
    }

    @Test
    fun thaiHouseholdIdJsonType() {
        ThaiHouseholdId("10245003328").toJson() `should equal json` """
{
  "type": "thailand-household-id",
  "id": "10245003328"
}
"""
    }
}

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

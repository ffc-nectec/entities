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

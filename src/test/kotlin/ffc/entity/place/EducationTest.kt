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

import org.amshove.kluent.`should be`
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class EducationTest(val name: String, val expected: Education) {

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
                arrayOf("เตรียมอนุบาล", Education.PreKindergarten),
                arrayOf("ก่อนอนุบาล", Education.PreKindergarten),
                arrayOf("มัธยม", Education.HighSchool),
                arrayOf("ประถม", Education.ElementarySchool)
        )
    }

    @Test
    fun byName() {
        Education.byName(name) `should be` expected
    }
}

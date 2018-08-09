/*
 * Copyright (c) 2018 NECTEC
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

import ffc.entity.healthcare.Chronic
import ffc.entity.util.generateTempId
import org.joda.time.LocalDate

class Person(id: String = generateTempId()) : Entity(id), Cloneable {

    var identities: MutableList<Identity> = mutableListOf()
    var prename: String = ""
    var firstname: String = ""
    var midname: String? = null
    var lastname: String = ""
    val name: String get() = "$prename$firstname ${midname?.plus(" ") ?: ""}$lastname".trim()
    var sex: Sex = Sex.UNKNOWN
    var birthDate: LocalDate? = null
    val age: Int? get() = birthDate?.let { LocalDate.now().year - it.year }
    var chronics: MutableList<Chronic> = mutableListOf()
    val haveChronic: Boolean get() = chronics.firstOrNull { it.isActive } != null
    var link: Link? = null

    enum class Sex {
        MALE, FEMALE, UNKNOWN
    }
}

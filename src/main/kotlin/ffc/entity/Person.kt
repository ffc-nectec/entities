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

import org.joda.time.LocalDate
import java.util.Random

data class Person(val id: Long = Random().nextLong() * -1) : Cloneable {

    var orgId: Int? = null
    var hospCode: String? = null
    var pid: Long? = null
    var prename: String = ""
    var firstname: String = ""
    var lastname: String = ""
    val name: String
        get() = "$prename$firstname $lastname"
    var birthDate: LocalDate? = null
    var identities: MutableList<Identity> = mutableListOf()
    var house: Address? = null
    var chronics: MutableList<Chronic>? = null
    var houseId: Int? = null
}

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

package ffc.entity.healthcare

import ffc.entity.Lookup
import ffc.entity.util.generateTempId

open class Disease(id: String, name: String) : Lookup(id, name)

class Icd10(
    name: String,
    val icd10: String,
    val isEpimedic: Boolean = false,
    val isChronic: Boolean = false,
    val isNCD: Boolean = false,
    id: String = generateTempId()
) : Disease(id, name) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Icd10) return false
        if (!super.equals(other)) return false
        if (icd10 != other.icd10) return false
        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + icd10.hashCode()
        return result
    }
}

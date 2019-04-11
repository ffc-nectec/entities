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

import ffc.entity.healthcare.Chronic
import ffc.entity.healthcare.Disability
import ffc.entity.healthcare.Disease
import ffc.entity.healthcare.analyze.HealthAnalyzer
import ffc.entity.util.checkValidUrl
import ffc.entity.util.generateTempId
import org.joda.time.LocalDate
import org.joda.time.Period

class Person(
    id: String = generateTempId(),
    var prename: String = "",
    var firstname: String = "",
    var midname: String? = null,
    var lastname: String = "",
    var sex: Sex = Sex.UNKNOWN,
    var birthDate: LocalDate? = null,
    var houseId: String = generateTempId()
) : Entity(id), Cloneable {
    var orgId: String? = null
    var identities: MutableList<Identity> = mutableListOf()
    val name: String get() = "$prename$firstname ${midname?.plus(" ") ?: ""}$lastname".trim()
    val age: Int? get() = birthDate?.let { Period(it, death?.date ?: LocalDate.now()).years }

    var chronics = mutableListOf<Chronic>()
    var disabilities = mutableListOf<Disability>()
    val haveChronic: Boolean get() = chronics.firstOrNull { it.isActive } != null
    var avatarUrl: String? = null
        set(url) {
            if (url != null) checkValidUrl(url)
            field = url
        }
    var link: Link? = null
    val isDead: Boolean
        get() = death != null
    var death: Death? = null
    var healthAnalyze: HealthAnalyzer? = null

    var relationships: MutableList<Relationship> = mutableListOf()
    val fatherId: String?
        get() = relationships.firstOrNull { it.relate == Relate.Father }?.id
    val motherId: String?
        get() = relationships.firstOrNull { it.relate == Relate.Mother }?.id
    val spouseId: String?
        get() = relationships.firstOrNull { it.relate == Relate.Married }?.id
    val siblingId: List<String>
        get() = relationships.filter { it.relate == Relate.Sibling }.map { it.id }
    val childId: List<String>
        get() = relationships.filter { it.relate == Relate.Child }.map { it.id }

    fun addRelationship(vararg pairs: Pair<Relate, Person>) {
        val tempRelation = relationships.addRelationship(id, *pairs)
        relationships = tempRelation
    }

    enum class Sex {
        MALE, FEMALE, UNKNOWN
    }

    class Relationship(
        val relate: Relate,
        val id: String,
        val name: String,
        val age: Int? = null,
        val avatarUrl: String? = null
    ) : Cloneable {

        constructor(type: Relate, with: Person) : this(type, with.id, with.name, with.age, with.avatarUrl)

        fun copy(): Relationship = clone() as Relationship
    }

    enum class Relate {
        Father, Mother, Sibling, Twin, Married, Seperated, LegallySeperated, Divorced, Engaged, LoveAffair, Child
    }

    class Death(val date: LocalDate, val causes: List<Disease>) {

        constructor(date: LocalDate, vararg causesOfDeath: Disease) : this(date, causesOfDeath.toList())
    }
}

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
import ffc.entity.util.checkValidUrl
import ffc.entity.util.generateTempId
import org.joda.time.LocalDate

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
    val age: Int? get() = birthDate?.let { LocalDate.now().year - it.year }
    var chronics: MutableList<Chronic> = mutableListOf()
    val haveChronic: Boolean get() = chronics.firstOrNull { it.isActive } != null
    var avatarUrl: String? = null
        set(url) {
            if (url != null) checkValidUrl(url)
            field = url
        }
    var link: Link? = null

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
        val tempRelation = relationships.addRelationship(*pairs)

        require(tempRelation.find { it.id == this.id } == null) { "ไม่สามารถมีความสัมพันธ์กับตัวเองได้" }

        relationships = tempRelation
    }

    enum class Sex {
        MALE, FEMALE, UNKNOWN
    }

    class Relationship(val relate: Relate, val id: String, val name: String) : Cloneable {

        constructor(type: Relate, with: Person) : this(type, with.id, with.name)

        fun copy(): Relationship = clone() as Relationship
    }

    enum class Relate {
        Father, Mother, Sibling, Twin, Married, Seperated, LegallySeperated, Divorced, Engaged, LoveAffair, Child
    }
}

private fun MutableList<Person.Relationship>.clone(): MutableList<Person.Relationship> {
    return map {
        it.copy()
    }.toMutableList()
}

/**
 * เพิ่มความสัมพันธ์ พร้อมตรวจสอบความถูกต้องของสายสัมพันธ์
 * @return ข้อมูลความสัมพันธ์ใหม่ ที่ผ่านการตรวจสอบความถูกต้องแล้ว
 */
fun MutableList<Person.Relationship>.addRelationship(vararg pairs: Pair<Person.Relate, Person>): MutableList<Person.Relationship> {

    val tempRelation = clone()
    pairs.forEach {
        tempRelation.add(Person.Relationship(it.first, it.second))
    }

    validateRelation(tempRelation)
    return tempRelation
}

fun List<Person.Relationship>.validate() {
    validateRelation(this)
}

private val validateRelation: (List<Person.Relationship>) -> Unit =
    { updateRelation: List<Person.Relationship> ->

        val groupPerson: HashMap<String, ArrayList<Person.Relationship>> = hashMapOf()
        updateRelation.forEach {
            if (groupPerson[it.id] == null) groupPerson[it.id] = arrayListOf()
            groupPerson[it.id]!!.add(it)
        }

        groupPerson.forEach { personId, relation ->
            if (relation.count() > 1) {
                val groupRelation: HashMap<Person.Relate, ArrayList<Person.Relationship>> = hashMapOf()
                relation.forEach {
                    if (groupRelation[it.relate] == null) groupRelation[it.relate] = arrayListOf()
                    groupRelation[it.relate]!!.add(it)
                }

                groupRelation.forEach { relateGroup, personRelation ->
                    require(personRelation.count() == 1) {
                        "พบการใส่ความสัมพันธ์ซ้ำ $relateGroup count ${personRelation.count()}"
                    }
                    conditionRelationShip(updateRelation, personRelation, relateGroup)
                }
            }
        }
    }

private fun conditionRelationShip(
    updateRelation: List<Person.Relationship>,
    personRelation: java.util.ArrayList<Person.Relationship>,
    relateGroup: Person.Relate
) {
    val personGroupRelation = updateRelation.filter { it.id == personRelation.first().id }
    /* ktlint-disable */
    when (relateGroup) {
        Person.Relate.Child -> {
            require(personGroupRelation `ต้องไม่มีความสัมพันธ์เป็น` Person.Relate.Mother) { "ตรวจพบความสัมพันธ์ในครอบครัวแปลก $relateGroup Mother is Child" }
            require(personGroupRelation `ต้องไม่มีความสัมพันธ์เป็น` Person.Relate.Father) { "ตรวจพบความสัมพันธ์ในครอบครัวแปลก $relateGroup Father is Child" }
            require(personGroupRelation `ต้องไม่มีความสัมพันธ์เป็น` Person.Relate.Married) { "ตรวจพบความสัมพันธ์ในครอบครัวแปลก $relateGroup Married with child" }
            require(personGroupRelation `ต้องไม่มีความสัมพันธ์เป็น` Person.Relate.Seperated) { "ตรวจพบความสัมพันธ์ในครอบครัวแปลก $relateGroup Separated with child" }
            require(personGroupRelation `ต้องไม่มีความสัมพันธ์เป็น` Person.Relate.LegallySeperated) { "ตรวจพบความสัมพันธ์ในครอบครัวแปลก $relateGroup Legally Separated with child" }
            require(personGroupRelation `ต้องไม่มีความสัมพันธ์เป็น` Person.Relate.Divorced) { "ตรวจพบความสัมพันธ์ในครอบครัวแปลก $relateGroup Divorced with child" }
        }
        Person.Relate.Married -> {
            require(personGroupRelation `ต้องไม่มีความสัมพันธ์เป็น` Person.Relate.Mother) { "ตรวจพบความสัมพันธ์ในครอบครัวแปลก $relateGroup Married with mother" }
            require(personGroupRelation `ต้องไม่มีความสัมพันธ์เป็น` Person.Relate.Father) { "ตรวจพบความสัมพันธ์ในครอบครัวแปลก $relateGroup Married with father" }

            require(personGroupRelation `ต้องไม่มีความสัมพันธ์เป็น` Person.Relate.Seperated) { "ตรวจพบความสัมพันธ์ในครอบครัวแปลก $relateGroup You have to choose Married, Divorced, Separated, LegallySeperated" }
            require(personGroupRelation `ต้องไม่มีความสัมพันธ์เป็น` Person.Relate.LegallySeperated) { "ตรวจพบความสัมพันธ์ในครอบครัวแปลก $relateGroup You have to choose Married, Divorced, Separated, LegallySeperated" }
            require(personGroupRelation `ต้องไม่มีความสัมพันธ์เป็น` Person.Relate.Divorced) { "ตรวจพบความสัมพันธ์ในครอบครัวแปลก $relateGroup You have to choose Married, Divorced, Separated, LegallySeperated" }
        }
    }
    /* ktlint-enable */
}

private infix fun List<Person.Relationship>.`ต้องไม่มีความสัมพันธ์เป็น`(relation: Person.Relate): Boolean =
    find { it.relate == relation } == null

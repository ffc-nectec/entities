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

import ffc.entity.gson.toJson
import ffc.entity.util.generateTempId
import org.amshove.kluent.`should contain all`
import org.amshove.kluent.`should equal`
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

@Suppress("NonAsciiCharacters", "RemoveRedundantBackticks")
class PersonRelationshipTest {

    private val อากง = male("อากง", "sukit_id")
    private val `อาม่า` = female("อาม่า", "pranee_id")
    private val `ประเสริฐ` = male("ประเสริฐ", "prasert_id")
    private val เมธ = male("เมธ", "mate_id")
    private val `ภัสสร` = female("ภัสสร", "patsorn_id")
    private val `กรกันต์` = male("กรกันต์", "kornkan_id")
    private val `มนฤดี` = female("มนฤดี", "monrudee_id")
    private val `นิภา` = female("นิภา", "nipa_id")
    private val `คริส` = female("คริส", "krist_id")
    private val `วิเชียร` = male("วิเชียร", "wichean_id")
    private val `น้ำผึ้ง` = female("น้ำผึ้ง", "nampung_id")
    private val `พีท` = male("พีท", "pete_id")
    private val `ฉี` = male("ฉี", "chi_id")
    private val `เหม่เหม` = female("เหม่เหม", "meimei_id")
    private val `อี้` = male("อี้", "ei_id")
    private val `เอิร์น` = male("เอิร์น", "earn_id")
    private val `เต๋า` = male("เต๋า", "tao_id")
    private val `เต้ย` = male("เต้ย", "toey_id")
    private val `ก๋วยเตี๋ยว` = male("ก๋วยเตี๋ยว", "kwayteow_id")
    private val `เวกัส` = male("เวกัส", "vegas_id")
    private val `มาเก๋า` = male("มาเก๋า", "macau_id")

    @Before
    fun setUp() {
        อากง.addRelationship(
            Person.Relate.Married to `อาม่า`,
            Person.Relate.Child to `ประเสริฐ`,
            Person.Relate.Child to เมธ,
            Person.Relate.Child to `ภัสสร`,
            Person.Relate.Child to `มนฤดี`,
            Person.Relate.Child to `กรกันต์`
        )

        `อาม่า`.addRelationship(
            Person.Relate.Married to อากง,
            Person.Relate.Child to `ประเสริฐ`,
            Person.Relate.Child to เมธ,
            Person.Relate.Child to `ภัสสร`,
            Person.Relate.Child to `มนฤดี`,
            Person.Relate.Child to `กรกันต์`
        )

        `ประเสริฐ`.addRelationship(
            Person.Relate.Father to อากง,
            Person.Relate.Mother to `อาม่า`,
            Person.Relate.Divorced to `คริส`,
            Person.Relate.Child to `พีท`,
            Person.Relate.Married to `นิภา`,
            Person.Relate.Child to `ฉี`,
            Person.Relate.Sibling to เมธ,
            Person.Relate.Sibling to `ภัสสร`,
            Person.Relate.Sibling to `มนฤดี`,
            Person.Relate.Sibling to `กรกันต์`
        )

        `นิภา`.addRelationship(
            Person.Relate.Married to `ประเสริฐ`,
            Person.Relate.Child to `ฉี`
        )

        `คริส`.addRelationship(
            Person.Relate.Married to `ประเสริฐ`,
            Person.Relate.Child to `พีท`
        )

        เมธ.addRelationship(
            Person.Relate.Father to อากง,
            Person.Relate.Mother to `อาม่า`,
            Person.Relate.Child to `เหม่เหม`,
            Person.Relate.Sibling to `ประเสริฐ`,
            Person.Relate.Sibling to `ภัสสร`,
            Person.Relate.Sibling to `มนฤดี`,
            Person.Relate.Sibling to `กรกันต์`
        )

        `ภัสสร`.addRelationship(
            Person.Relate.Father to อากง,
            Person.Relate.Mother to `อาม่า`,
            Person.Relate.Married to `วิเชียร`,
            Person.Relate.Child to `อี้`,
            Person.Relate.Child to `เอิร์น`,
            Person.Relate.Child to `เต๋า`,
            Person.Relate.Child to `เต้ย`,
            Person.Relate.Sibling to `ประเสริฐ`,
            Person.Relate.Sibling to `เมธ`,
            Person.Relate.Sibling to `มนฤดี`,
            Person.Relate.Sibling to `กรกันต์`
        )

        `วิเชียร`.addRelationship(
            Person.Relate.Married to `ภัสสร`,
            Person.Relate.Child to `อี้`,
            Person.Relate.Child to `เอิร์น`,
            Person.Relate.Child to `เต๋า`,
            Person.Relate.Child to `เต้ย`
        )

        `มนฤดี`.addRelationship(
            Person.Relate.Father to อากง,
            Person.Relate.Mother to `อาม่า`,
            Person.Relate.Child to `ก๋วยเตี๋ยว`
        )

        `กรกันต์`.addRelationship(
            Person.Relate.Father to อากง,
            Person.Relate.Mother to `อาม่า`,
            Person.Relate.Married to `น้ำผึ้ง`,
            Person.Relate.Child to `เวกัส`,
            Person.Relate.Child to `มาเก๋า`,
            Person.Relate.Sibling to `ประเสริฐ`,
            Person.Relate.Sibling to `เมธ`,
            Person.Relate.Sibling to `ภัสสร`,
            Person.Relate.Sibling to `มนฤดี`
        )

        `น้ำผึ้ง`.addRelationship(
            Person.Relate.Married to `กรกันต์`,
            Person.Relate.Child to `เวกัส`,
            Person.Relate.Child to `มาเก๋า`
        )

        `ฉี`.addRelationship(
            Person.Relate.Father to `ประเสริฐ`,
            Person.Relate.Mother to `นิภา`
        )

        `พีท`.addRelationship(
            Person.Relate.Father to `ประเสริฐ`,
            Person.Relate.Mother to `คริส`
        )

        `เหม่เหม`.addRelationship(
            Person.Relate.Father to เมธ
        )

        `ก๋วยเตี๋ยว`.addRelationship(
            Person.Relate.Father to `มนฤดี`
        )

        `อี้`.addRelationship(
            Person.Relate.Father to `วิเชียร`,
            Person.Relate.Mother to `ภัสสร`,
            Person.Relate.Sibling to `เอิร์น`,
            Person.Relate.Sibling to `เต๋า`,
            Person.Relate.Sibling to `เต้ย`
        )

        `เอิร์น`.addRelationship(
            Person.Relate.Father to `วิเชียร`,
            Person.Relate.Mother to `ภัสสร`,
            Person.Relate.Sibling to `อี้`,
            Person.Relate.Sibling to `เต๋า`,
            Person.Relate.Sibling to `เต้ย`
        )

        `เต๋า`.addRelationship(
            Person.Relate.Father to `วิเชียร`,
            Person.Relate.Mother to `ภัสสร`,
            Person.Relate.Sibling to `อี้`,
            Person.Relate.Sibling to `เอิร์น`,
            Person.Relate.Sibling to `เต้ย`
        )

        `เต้ย`.addRelationship(
            Person.Relate.Father to `วิเชียร`,
            Person.Relate.Mother to `ภัสสร`,
            Person.Relate.Sibling to `อี้`,
            Person.Relate.Sibling to `เอิร์น`,
            Person.Relate.Sibling to `เต๋า`
        )

        `เวกัส`.addRelationship(
            Person.Relate.Father to `กรกันต์`,
            Person.Relate.Mother to `น้ำผึ้ง`,
            Person.Relate.Sibling to `มาเก๋า`
        )

        `มาเก๋า`.addRelationship(
            Person.Relate.Father to `กรกันต์`,
            Person.Relate.Mother to `น้ำผึ้ง`,
            Person.Relate.Sibling to `เวกัส`
        )
    }

    fun male(name: String, key: String = name): Person {
        return Person("{{$key}}").apply {
            firstname = name
            lastname = "จิระอนันต์"
            sex = Person.Sex.MALE
            houseId = "{{house_id_for_geno}}"
        }
    }

    fun female(name: String, key: String = name): Person {
        return Person("{{$key}}").apply {
            firstname = name
            lastname = "จิระอนันต์"
            sex = Person.Sex.FEMALE
            houseId = "{{house_id_for_geno}}"
        }
    }

    @Test
    fun getFather() {
        `ประเสริฐ`.fatherId `should equal` อากง.id
    }

    @Test
    fun getMother() {
        `ประเสริฐ`.motherId `should equal` `อาม่า`.id
    }

    @Test
    fun getSpouse() {
        `ประเสริฐ`.spouseId `should equal` `นิภา`.id
    }

    @Test
    fun getChild() {
        `ประเสริฐ`.childId `should contain all` listOf(`ฉี`.id, `พีท`.id)
    }

    @Test
    fun getSibling() {
        `ประเสริฐ`.siblingId `should contain all` listOf(เมธ.id, `ภัสสร`.id, `กรกันต์`.id)
    }

    @Test(expected = java.lang.IllegalArgumentException::class)
    fun validateDuplicateRelation() {
        `ประเสริฐ`.addRelationship(Person.Relate.Child to `นิภา`)
    }

    @Test(expected = java.lang.IllegalArgumentException::class)
    fun validateRelationMe() {
        `ประเสริฐ`.addRelationship(Person.Relate.Married to `ประเสริฐ`)
    }

    @Test(expected = java.lang.IllegalArgumentException::class)
    fun validateMotherChild() {
        `ประเสริฐ`.addRelationship(Person.Relate.Child to `อาม่า`)
    }

    @Test(expected = java.lang.IllegalArgumentException::class)
    fun validateFatherChild() {
        `ประเสริฐ`.addRelationship(Person.Relate.Child to `อากง`)
    }

    @Test(expected = java.lang.IllegalArgumentException::class)
    fun childMarriedFather() {
        `ประเสริฐ`.addRelationship(Person.Relate.Married to `พีท`)
    }

    @Test
    fun toGenogramFamily() {
        //TODO implement
    }

    @Ignore("for create test data")
    @Test
    fun toJson() {
        val person = `คริส`
        println(person.relationships.toJson())
        val newPerson = person.copy(generateTempId())
        newPerson.relationships = mutableListOf()
        println(newPerson.toJson())
    }
}

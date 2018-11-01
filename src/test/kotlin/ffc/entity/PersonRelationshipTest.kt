package ffc.entity

import org.amshove.kluent.`should contain all`
import org.amshove.kluent.`should equal`
import org.junit.Before
import org.junit.Test
import ffc.genogram.Person as GenoPerson

class PersonRelationshipTest {

    private val อากง = male("อากง")
    private val `อาม่า` = female("อาม่า")
    private val `ประเสริฐ` = male("ประเสริฐ")
    private val เมธ = male("เมธ")
    private val `ภัสสร` = female("ภัสสร")
    private val `กรกันต์` = male("กรกันต์")
    private val `มนฤดี` = female("มนฤดี")
    private val `นิภา` = female("นิภา")
    private val `คริส` = female("คริส")
    private val `วิเชียร` = male("วิเชียร")
    private val `น้ำผึ้ง` = female("น้ำผึ้ง")
    private val `พีท` = male("พีท")
    private val `ฉี` = male("ฉี")
    private val `เหม่เหม` = female("เหม่เหม")
    private val `อี้` = male("อี้")
    private val `เอิร์น` = male("เอิร์น")
    private val `เต๋า` = male("เต๋า")
    private val `เต้ย` = male("เต้ย")
    private val `ก๋วยเตี๋ยว` = male("ก๋วยเตี๋ยว")
    private val `เวกัส` = male("เวกัส")
    private val `มาเก๋า` = male("มาเก๋า")

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

    fun male(name: String): Person {
        return Person().apply {
            firstname = name
            lastname = "จิระอนันต์"
            sex = Person.Sex.MALE
        }
    }

    fun female(name: String): Person {
        return Person().apply {
            firstname = name
            lastname = "จิระอนันต์"
            sex = Person.Sex.FEMALE
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
}

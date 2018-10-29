package ffc.entity

import org.amshove.kluent.`should contain all`
import org.amshove.kluent.`should equal`
import org.junit.Test

class PersonRelationshipTest {

    private val อากง = male("อากง")
    private val `อาม่า` = female("อาม่า")
    private val `นิภา` = female("นิภา")
    private val `คริส` = female("คริส")
    private val `ฉี` = male("ฉี")
    private val `พีท` = male("พีท")
    private val เมธ = male("เมธ")
    private val `ภัสสร` = female("ภัสสร")
    private val `กรกันต์` = male("กรกันต์")

    private val `ประเสริฐ` = male("ประเสริฐ").apply {
        addRelationship(
            Person.Relate.Father to อากง,
            Person.Relate.Married to `นิภา`,
            Person.Relate.Divorced to `คริส`,
            Person.Relate.Mother to `อาม่า`,
            Person.Relate.Child to `พีท`,
            Person.Relate.Child to `ฉี`,
            Person.Relate.Sibling to เมธ,
            Person.Relate.Sibling to `ภัสสร`,
            Person.Relate.Sibling to `กรกันต์`
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
}

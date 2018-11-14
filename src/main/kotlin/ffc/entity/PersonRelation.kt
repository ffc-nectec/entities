package ffc.entity

private fun MutableList<Person.Relationship>.clone(): MutableList<Person.Relationship> {
    return map {
        it.copy()
    }.toMutableList()
}

/**
 * เพิ่มความสัมพันธ์ พร้อมตรวจสอบความถูกต้องของสายสัมพันธ์
 * @return ข้อมูลความสัมพันธ์ใหม่ ที่ผ่านการตรวจสอบความถูกต้องแล้ว
 */
internal fun MutableList<Person.Relationship>.addRelationship(
    personId: String,
    vararg pairs: Pair<Person.Relate, Person>
): MutableList<Person.Relationship> {

    val tempRelation = clone()
    pairs.forEach {
        tempRelation.add(Person.Relationship(it.first, it.second))
    }

    validateRelation(personId, tempRelation)
    return tempRelation
}

fun List<Person.Relationship>.validate(id: String = "") {
    validateRelation(id, this)
}

private val validateRelation: (String, List<Person.Relationship>) -> Unit =
        { ownPersonId, updateRelation ->

            if (ownPersonId.isNotEmpty())
                require(updateRelation.find { it.id == ownPersonId } == null) { "ไม่สามารถมีความสัมพันธ์กับตัวเองได้" }

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

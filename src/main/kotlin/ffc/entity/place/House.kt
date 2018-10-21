package ffc.entity.place

import ffc.entity.Person
import ffc.entity.Place
import ffc.entity.ThaiHouseholdId
import ffc.entity.util.generateTempId

class House(id: String = generateTempId()) : Place(id) {
    var identity: ThaiHouseholdId? = null
    var people: MutableList<Person> = mutableListOf()
    var haveChronic: Boolean = false
        get() = if (people.isNotEmpty()) people.firstOrNull { it.haveChronic } != null else field
}

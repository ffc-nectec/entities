package ffc.entity

import java.time.LocalDate
import java.util.Random

data class Person(val id: Long = Random().nextLong() * -1) {

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

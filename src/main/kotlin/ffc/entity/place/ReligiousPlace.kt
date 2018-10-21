package ffc.entity.place

import ffc.entity.Place
import ffc.entity.util.generateTempId

class ReligiousPlace(id: String = generateTempId()) : Place(id) {
    lateinit var name: String
    var religion: Religion? = null
}

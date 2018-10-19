package ffc.entity.place

import ffc.entity.Place
import ffc.entity.util.generateTempId

class School(id: String = generateTempId()) : Place(id) {
    lateinit var name: String
    var maxclass: ClassSchool? = null
    var depend: String? = null
}

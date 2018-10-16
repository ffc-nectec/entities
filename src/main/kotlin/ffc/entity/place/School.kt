package ffc.entity.place

import ffc.entity.Place
import ffc.entity.util.generateTempId

class School(id: String = generateTempId()) : Place(id) {
    lateinit var name: String
    var maxclass: String? = null
    val depen: String? = null
}

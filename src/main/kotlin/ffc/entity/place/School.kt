package ffc.entity.place

import ffc.entity.Place
import ffc.entity.util.generateTempId

class School(id: String = generateTempId()) : Place(id) {
    lateinit var name: String
    lateinit var educationLevel: Education
    var isPrivate: Boolean = false
    var depend: String? = null
}

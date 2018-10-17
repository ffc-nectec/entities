package ffc.entity.place

import ffc.entity.Place
import ffc.entity.util.generateTempId

class Business(id: String = generateTempId()) : Place(id) {
    lateinit var name: String
    var businessType: String? = null
}

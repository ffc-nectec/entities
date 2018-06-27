package ffc.entity

import ffc.entity.util.generateTempId

class Village(id: String = generateTempId()) : Entity(id) {

    lateinit var name: String
    var places: MutableList<Place> = mutableListOf()
    var link: Link? = null
}

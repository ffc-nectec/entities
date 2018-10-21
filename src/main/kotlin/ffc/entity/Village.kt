package ffc.entity

import ffc.entity.util.centroidOf
import ffc.entity.util.generateTempId
import me.piruin.geok.geometry.Point

class Village(id: String = generateTempId()) : Entity(id) {

    lateinit var name: String
    var orgId: String? = null
    val location: Point
        get() = centroidOf(places.filter { it.location != null }.map { it.location!! })
    var places: MutableList<Place> = mutableListOf()
    var link: Link? = null
}

package ffc.entity

import me.piruin.geok.LatLng
import me.piruin.geok.geometry.Point
import org.joda.time.DateTime

data class Address(var _id: String = "", var dateUpdate: DateTime = DateTime.now()) {

    var _shortId: String = ""
    var identity: Identity? = null
    var type: Type = Type.House
    var no: String? = null
    var road: String? = null
    var tambon: String? = null
    var ampur: String? = null
    var changwat: String? = null
    @Deprecated("Use location", ReplaceWith("location"))
    var coordinates: LatLng? = null
    var location: Point? = null
    var hid: Int? = null
    var haveChronics: Boolean? = null
    var people: List<People>? = null
    var pcuCode: String? = null
    var _sync: Boolean = false

    enum class Type {
        House, Condo
    }

    fun clone(): Address {
        val cloneObj = Address(this._id, this.dateUpdate)
        cloneObj._shortId = this._shortId
        cloneObj.identity = this.identity
        cloneObj.type = this.type
        cloneObj.no = this.no
        cloneObj.road = this.road
        cloneObj.tambon = this.tambon
        cloneObj.ampur = this.ampur
        cloneObj.changwat = this.changwat
        cloneObj.coordinates = this.coordinates
        cloneObj.hid = this.hid
        cloneObj.haveChronics = this.haveChronics
        cloneObj.people = this.people
        cloneObj.pcuCode = this.pcuCode
        cloneObj._sync = this._sync

        return cloneObj
    }
}

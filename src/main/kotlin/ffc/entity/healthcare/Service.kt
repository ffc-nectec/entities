package ffc.entity.healthcare

import ffc.entity.Entity
import ffc.entity.Link
import ffc.entity.util.generateTempId
import me.piruin.geok.geometry.Point
import org.joda.time.DateTime

abstract class Service(
    val providerId: String,
    val patientId: String,
    id: String = generateTempId()
) : Entity(id) {
    var time: DateTime = DateTime.now()
    var endTime: DateTime = time.plusMinutes(5)
        set(value) {
            require(value.isAfter(time)) { "endTime must be after time" }
            field = value
        }
    var location: Point? = null
    var link: Link? = null

    fun mapTo(other: Service) {
        other.let {
            it.time = time
            it.endTime = endTime
            it.location = location
        }
    }
}

package ffc.entity.healthcare

import ffc.entity.Entity
import ffc.entity.Link
import ffc.entity.Person
import ffc.entity.User
import ffc.entity.util.generateTempId
import me.piruin.geok.geometry.Point
import org.joda.time.DateTime

open class HealthCareService(id: String = generateTempId()) : Entity(id) {

    lateinit var patient: Person
    lateinit var provider: User

    var time = DateTime.now()
    var location: Point? = null

    var syntom: String? = null
    var suggestion: String? = null

    var weight: Double? = null
    var height: Double? = null
    val bmi: BodyMassIndex?
        get() {
            val h = height
            val w = weight
            return if (w != null && h != null) ThaiBMI(h / 100, w) else null
        }

    var bloodPressure: BloodPressure? = null
    var pulseRate: Number? = null
    var respiratoryRate: Number? = null

    var diagnosises: MutableList<Diagnosis> = mutableListOf()

    var link: Link? = null
}

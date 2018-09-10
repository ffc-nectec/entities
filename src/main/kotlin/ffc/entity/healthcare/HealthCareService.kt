package ffc.entity.healthcare

import ffc.entity.Entity
import ffc.entity.Link
import ffc.entity.util.generateTempId
import me.piruin.geok.geometry.Point
import org.joda.time.DateTime

open class HealthCareService(
    val providerId: String,
    val patientId: String,
    id: String = generateTempId()
) : Entity(id) {
    var time: DateTime = DateTime.now()
    var endTime: DateTime = time.plusMinutes(5)
    var location: Point? = null
    var syntom: String? = null
    var suggestion: String? = null
    var weight: Double? = null
    var height: Double? = null
    val bmi: BodyMassIndex?
        get() {
            val h = height
            val w = weight
            return if (w != null && h != null) bmi(h / 100, w) else null
        }
    var bloodPressure: BloodPressure? = null
    var pulseRate: Double? = null
    var respiratoryRate: Double? = null
    var bodyTemperature: Double? = null
    var diagnosises: MutableList<Diagnosis> = mutableListOf()
    var link: Link? = null

    var principleDx: Disease?
        get() = diagnosises.firstOrNull { it.dxType == Diagnosis.Type.PRINCIPLE_DX }?.disease
        set(value) {
            if (value != null)
                diagnosises.add(Diagnosis(value, Diagnosis.Type.PRINCIPLE_DX))
            else
                diagnosises.removeIf { it.dxType == Diagnosis.Type.PRINCIPLE_DX }
        }
}

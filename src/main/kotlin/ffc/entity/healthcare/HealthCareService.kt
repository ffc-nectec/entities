package ffc.entity.healthcare

import ffc.entity.util.URLs
import ffc.entity.util.generateTempId
import org.joda.time.LocalDate

open class HealthCareService(
    providerId: String,
    patientId: String,
    id: String = generateTempId()
) : Service(providerId, patientId, id) {
    var nextAppoint: LocalDate? = null
    var syntom: String? = null
    var suggestion: String? = null
    var weight: Double? = null
    var height: Double? = null
    var waist: Double? = null
    var ass: Double? = null
    val bmi: BodyMassIndex?
        get() {
            val h = height
            val w = weight
            return if (w != null && h != null) bmi(h / 100, w) else null
        }
    var bloodPressure: BloodPressure? = null
    var bloodPressure2nd: BloodPressure? = null
    var pulseRate: Double? = null
    var respiratoryRate: Double? = null
    var bodyTemperature: Double? = null
    var diagnosises: MutableList<Diagnosis> = mutableListOf()
    var note: String? = null
    var photosUrl: URLs = URLs()

    var communityServices: MutableList<CommunityService> = mutableListOf()
    var ncdScreen: NCDScreen? = null
        set(value) {
            value?.let {
                this.mapTo(it)
                it.height = height
                it.weight = weight
                it.waist = waist
                it.bloodPressure = bloodPressure
                it.bloodPressure2nd = bloodPressure2nd
            }
            field = value
        }

    var specialPPs: MutableList<SpecialPP> = mutableListOf()
    fun addSpecialPP(ppType: SpecialPP.PPType, block: (SpecialPP.() -> Unit)? = null) {
        val pp = SpecialPP(providerId, patientId, ppType, id)
        block?.let { pp.apply(it) }
        this.mapTo(pp)
        specialPPs.add(pp)
    }

    var principleDx: Disease?
        get() = diagnosises.firstOrNull { it.dxType == Diagnosis.Type.PRINCIPLE_DX }?.disease
        set(value) {
            if (value != null)
                diagnosises.add(Diagnosis(value, Diagnosis.Type.PRINCIPLE_DX))
            else
                diagnosises.removeIf { it.dxType == Diagnosis.Type.PRINCIPLE_DX }
        }
}

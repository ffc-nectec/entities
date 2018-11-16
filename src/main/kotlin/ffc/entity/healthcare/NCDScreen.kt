package ffc.entity.healthcare

import ffc.entity.util.generateTempId

class NCDScreen(
    providerId: String,
    patientId: String,
    val hasDmInFamily: Boolean? = null,
    val hasHtInFamily: Boolean? = null,
    val smoke: Frequency = Frequency.UNKNOWN,
    val alcohol: Frequency = Frequency.UNKNOWN,
    val bloodSugar: Double? = null,
    var weight: Double? = null,
    var height: Double? = null,
    var waist: Double? = null,
    var bloodPressure: BloodPressure? = null,
    var bloodPressure2nd: BloodPressure? = null,
    id: String = generateTempId()
) : Services(providerId, patientId, id)

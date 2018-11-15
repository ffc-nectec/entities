package ffc.entity.healthcare

import ffc.entity.util.generateTempId

class NCDScreen(
    providerId: String,
    patientId: String,
    val hasDmInFamily: Boolean? = null,
    val hasHtInFamily: Boolean? = null,
    val smoke: Frequency? = null,
    val alcohol: Frequency? = null,
    val bloodSugar: Double? = null,
    id: String = generateTempId()
) : Services(providerId, patientId, id)

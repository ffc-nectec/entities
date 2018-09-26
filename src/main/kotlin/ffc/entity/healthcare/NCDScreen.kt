package ffc.entity.healthcare

import ffc.entity.util.generateTempId

class NCDScreen(
    providerId: String,
    patientId: String,
    id: String = generateTempId(),
    val hasDmInFamily: Boolean? = null,
    val hasHtInFamily: Boolean? = null,
    val smoke: Frequency? = null,
    val alcohol: Frequency? = null,
    val bloodSugar: Double? = null
) : HealthCareService(providerId, patientId, id)

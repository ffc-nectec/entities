package ffc.entity.healthcare

import ffc.entity.util.generateTempId
import org.joda.time.LocalDate

class HomeVisit(
    providerId: String,
    patientId: String,
    var serviceType: CommunityServiceType,
    id: String = generateTempId()
) : HealthCareService(providerId, patientId, id) {
    var detail: String? = null
    var result: String? = null
    var plan: String? = null
    var nextAppoint: LocalDate? = null
}

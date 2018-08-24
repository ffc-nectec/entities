package ffc.entity.healthcare

import ffc.entity.util.generateTempId

class HomeVisit(
    providerId: String,
    patientId: String,
    var serviceType: CommunityServiceType,
    id: String = generateTempId()
) : HealthCareService(patientId, providerId, id) {
    var detail: String? = null
    var result: String? = null
    var plan: String? = null
}

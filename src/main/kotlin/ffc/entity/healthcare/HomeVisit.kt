package ffc.entity.healthcare

import ffc.entity.util.generateTempId

class HomeVisit(
    var serviceCause: CommunityServiceType,
    id: String = generateTempId()
) : HealthCareService(id) {

    var detail: String? = null
    var result: String? = null
    var plan: String? = null
}

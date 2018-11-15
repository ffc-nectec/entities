package ffc.entity.healthcare

import ffc.entity.Lookup

open class CommunityService(val serviceType: ServiceType) {
    val type = javaClass.simpleName

    class ServiceType(id: String, name: String) : Lookup(id, name)
}

class HomeVisit(
    serviceType: CommunityService.ServiceType,
    var detail: String? = null,
    var result: String? = null,
    var plan: String? = null
) : CommunityService(serviceType) {

    init {
        require(serviceType.id.startsWith("1A"))
    }
}

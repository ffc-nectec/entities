package ffc.entity.healthcare

import ffc.entity.User

fun User.homeVisit(patientId: String, serviceType: CommunityServiceType): HomeVisit {
    require(roles.contains(User.Role.PROVIDER)) { "Only user with Provider role can use useVisit" }

    return HomeVisit(this.id, patientId, serviceType)
}

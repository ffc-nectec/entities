package ffc.entity

import java.util.UUID

data class Organization(val uuid: UUID = UUID.randomUUID()) : Cloneable {

    constructor(uuid: UUID = UUID.randomUUID(), block: Organization.() -> Unit) : this(uuid) {
        apply(block)
    }

    var id: String = "-1"
    var pcuCode: String = "099912"
    var name: String = "NECTEC"
    var session: String? = null
    var lastKnownIp: String? = null
    var token: UUID? = null
    var socketUrl: String? = null
    var firebaseToken: String? = null

    override fun clone(): Organization {
        return super.clone() as Organization
    }
}

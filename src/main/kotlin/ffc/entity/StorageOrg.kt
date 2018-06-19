package ffc.entity

import java.util.UUID

data class StorageOrg<T>(
    val uuid: UUID,
    var data: T,
    var user: String? = null,
    var orgId: String? = null
)

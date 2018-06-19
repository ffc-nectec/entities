package ffc.entity

import java.util.UUID

data class UserStor(
    val user: User,
    val orgUuid: UUID,
    val orgId: String
)

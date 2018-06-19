package ffc.entity

import java.util.UUID

//username password mobileuuid orgUuid check in header set Action is Regis
data class UserInfo(
    val user: User,
    val mobileUuid: UUID,
    val orgUuid: UUID,
    var checkUser: UserStatus = UserStatus.VALIDATE
) {
    fun getKey(): String {
        return orgUuid.toString() + "_" + mobileUuid
    }

    enum class UserStatus() {
        VALIDATE, PASS, NOTPASS
    }
}

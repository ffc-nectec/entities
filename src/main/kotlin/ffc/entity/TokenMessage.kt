package ffc.entity

import org.joda.time.DateTime
import java.util.UUID

val USERDATEEXPIRE = 1
val ORGDATEEXPIRE = 9000

data class TokenMessage(
    val token: UUID,
    var firebaseToken: String? = null,
    val timestamp: DateTime = DateTime.now(),
    val role: TYPEROLE = TYPEROLE.NOAUTH,
    val name: String
) {

    var expireDate: DateTime = when (role) {
        TYPEROLE.USER -> timestamp.plusDays(USERDATEEXPIRE)
        TYPEROLE.ORG -> timestamp.plusDays(ORGDATEEXPIRE)
        else -> timestamp
    }

    fun checkExpireTokem(): Boolean = expireDate.isBeforeNow

    enum class TYPEROLE {
        ORG, USER, NOAUTH
    }
}

package ffc.entity.healthcare

import ffc.entity.Lookup
import ffc.entity.util.generateTempId

class SpecialPP(
    providerId: String,
    patientId: String,
    val ppType: PPType,
    id: String = generateTempId()
) : Service(providerId, patientId, id) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SpecialPP) return false
        if (ppType != other.ppType) return false
        return true
    }

    override fun hashCode(): Int = ppType.hashCode()

    class PPType(
        id: String = generateTempId(),
        name: String
    ) : Lookup(id, name)
}

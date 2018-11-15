package ffc.entity.healthcare

import ffc.entity.util.generateTempId

class SpecialPP(
    providerId: String,
    patientId: String,
    val ppType: PPType,
    id: String = generateTempId()
) : HealthCareService(providerId, patientId, id) {

    class PPType(
        name: String,
        val code: String,
        id: String = generateTempId()
    ) : Disease(id, name) {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is PPType) return false
            if (!super.equals(other)) return false
            if (code != other.code) return false
            return true
        }

        override fun hashCode(): Int {
            var result = super.hashCode()
            result = 31 * result + code.hashCode()
            return result
        }
    }
}

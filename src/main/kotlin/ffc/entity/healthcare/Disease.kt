package ffc.entity.healthcare

import ffc.entity.Lookup
import ffc.entity.util.generateTempId

open class Disease(id: String, name: String) : Lookup(id, name)

class Icd10(
    name: String,
    val icd10: String,
    val isEpimedic: Boolean = false,
    val isChronic: Boolean = false,
    val isNCD: Boolean = false,
    id: String = generateTempId()
) : Disease(id, name) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Icd10) return false
        if (!super.equals(other)) return false
        if (icd10 != other.icd10) return false
        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + icd10.hashCode()
        return result
    }
}

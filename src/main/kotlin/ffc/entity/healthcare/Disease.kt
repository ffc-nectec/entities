package ffc.entity.healthcare

import ffc.entity.Entity
import ffc.entity.util.generateTempId

class Disease(val name: String, icd10: String? = null, id: String = generateTempId()) : Entity(id) {
    var icd10: String? = icd10
    var isChronic: Boolean = false
    var isNonCommunicable: Boolean = false
}

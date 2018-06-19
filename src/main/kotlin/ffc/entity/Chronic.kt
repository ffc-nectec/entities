package ffc.entity

import org.joda.time.LocalDate

data class Chronic(val idc10: String, val diagDate: LocalDate) {
    var diagHospCode: String? = null
    var careHospCode: String? = null
    var status: String = "active"
    var dischardDate: LocalDate? = null
    var houseId: Int? = null
    var pid: Long? = null
}

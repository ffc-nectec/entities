package ffc.entity.healthcare

import ffc.entity.Link
import org.joda.time.LocalDate

data class Chronic(val disease: Disease) : Cloneable {
    var diagDate: LocalDate = LocalDate.now()
    var dischardDate: LocalDate? = null
    val isActive: Boolean get() = dischardDate == null
    var link: Link? = null

    public override fun clone(): Chronic = super.clone() as Chronic
}

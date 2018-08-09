package ffc.entity.healthcare

abstract class BloodPressure(val systolic: Double, val diastolic: Double) {

    abstract val isLow: Boolean
    abstract val isNormal: Boolean
    abstract val isPreHigh: Boolean
    abstract val isHigh: Boolean

    override fun toString() = "$systolic/$diastolic mmHg"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BloodPressure

        if (systolic != other.systolic) return false
        if (diastolic != other.diastolic) return false

        return true
    }

    override fun hashCode(): Int {
        var result = systolic.hashCode()
        result = 31 * result + diastolic.hashCode()
        return result
    }
}

class ThaiBloodPressure(systolic: Double, diastolic: Double) : BloodPressure(systolic, diastolic) {

    override val isLow: Boolean
        get() = systolic <= 90 || diastolic <= 60
    override val isNormal: Boolean
        get() = systolic in 91..120 && diastolic in 61..80
    override val isPreHigh: Boolean
        get() = (systolic in 121..140 || diastolic in 81..90) && !isHigh
    override val isHigh: Boolean
        get() = systolic > 140 || diastolic > 90
}

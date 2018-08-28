package ffc.entity.healthcare

data class BloodPressure(val systolic: Double, val diastolic: Double)

abstract class BloodPressureAnalyzer(val bp: BloodPressure) {

    abstract val isLow: Boolean
    abstract val isNormal: Boolean
    abstract val isPreHigh: Boolean
    abstract val isHigh: Boolean
}

internal class ThaiBloodPressure(bp: BloodPressure) : BloodPressureAnalyzer(bp) {

    constructor(systolic: Double, diastolic: Double) : this(BloodPressure(systolic, diastolic))

    val systolic = bp.systolic
    val diastolic = bp.diastolic

    override val isLow: Boolean
        get() = systolic <= 90 || diastolic <= 60
    override val isNormal: Boolean
        get() = systolic in 91..120 && diastolic in 61..80
    override val isPreHigh: Boolean
        get() = (systolic in 121..140 || diastolic in 81..90) && !isHigh
    override val isHigh: Boolean
        get() = systolic > 140 || diastolic > 90
}

val HealthCareService.bloodPressureLevel: BloodPressureAnalyzer?
    get() = bloodPressure?.let { ThaiBloodPressure(it) }

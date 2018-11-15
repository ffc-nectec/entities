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
        get() = systolic < 90 || diastolic < 60
    override val isNormal: Boolean
        get() = systolic in 90.0..119.0 && diastolic in 60.0..79.0
    override val isPreHigh: Boolean
        get() = (systolic in 120.0..139.0 || diastolic in 80.0..89.0) && !isHigh
    override val isHigh: Boolean
        get() = systolic >= 140.0 || diastolic >= 90.0
}

val HealthCareService.bloodPressureLevel: BloodPressureAnalyzer?
    get() = (bloodPressure2nd ?: bloodPressure)?.let { ThaiBloodPressure(it) }

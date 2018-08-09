package ffc.entity.healthcare

import kotlin.math.pow

abstract class BodyMassIndex(val heightMetre: Double, val weight: Double) {

    val value: Double = (weight / (heightMetre * heightMetre)).round(1)

    abstract val isUnderWeight: Boolean
    abstract val isNormal: Boolean
    abstract val isOverweight: Boolean
    abstract val isObese: Boolean

    fun Double.round(digitLength: Int): Double {
        val pow = 10.0.pow(digitLength)
        return Math.round(this * pow) / pow
    }
}

class ThaiBMI(h: Double, w: Double) : BodyMassIndex(h, w) {
    override val isUnderWeight: Boolean
        get() = value < 18.5
    override val isNormal: Boolean
        get() = value in 18.5..25.0
    override val isOverweight: Boolean
        get() = value in 25.1..30.0
    override val isObese: Boolean
        get() = value > 30.0
}

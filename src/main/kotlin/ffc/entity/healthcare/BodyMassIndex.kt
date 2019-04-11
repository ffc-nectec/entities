/*
 * Copyright 2019 NECTEC
 *   National Electronics and Computer Technology Center, Thailand
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

internal class ThaiBMI(h: Double, w: Double) : BodyMassIndex(h, w) {
    override val isUnderWeight: Boolean
        get() = value < 18.5
    override val isNormal: Boolean
        get() = value in 18.5..25.0
    override val isOverweight: Boolean
        get() = value in 25.1..30.0
    override val isObese: Boolean
        get() = value > 30.0
}

fun bmi(h: Double, w: Double): BodyMassIndex = ThaiBMI(h, w)

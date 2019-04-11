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

val NCDScreen.bloodPressureLevel: BloodPressureAnalyzer?
    get() = (bloodPressure2nd ?: bloodPressure)?.let { ThaiBloodPressure(it) }

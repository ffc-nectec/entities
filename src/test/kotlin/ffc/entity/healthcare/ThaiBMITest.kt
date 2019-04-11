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

import org.amshove.kluent.`should be`
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ThaiBMITest(
    val bmi: BodyMassIndex,
    val isUnderWeight: Boolean,
    val isNormal: Boolean,
    val isOverWeight: Boolean,
    val isObese: Boolean
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
                arrayOf(ThaiBMI(1.60, 40.0), true, false, false, false),
                arrayOf(ThaiBMI(1.60, 60.0), false, true, false, false),
                arrayOf(ThaiBMI(1.80, 60.0), false, true, false, false),
                arrayOf(ThaiBMI(1.65, 70.0), false, false, true, false),
                arrayOf(ThaiBMI(1.65, 85.0), false, false, false, true)
        )
    }

    @Test
    fun isUnderWeight() {
        bmi.isUnderWeight `should be` isUnderWeight
    }

    @Test
    fun isNormal() {
        bmi.isNormal `should be` isNormal
    }

    @Test
    fun isOverweight() {
        bmi.isOverweight `should be` isOverWeight
    }

    @Test
    fun isObese() {
        bmi.isObese `should be` isObese
    }
}

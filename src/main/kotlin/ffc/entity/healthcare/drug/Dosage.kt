/*
 * Copyright (c) 2019 NECTEC
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

package ffc.entity.healthcare.drug

open class Dosage(
    val amount: Double,
    val unit: Unit = Unit.UNKNOWN,
    val takeUntilFinish: Boolean = false
)

class MealDosage(
    meals: List<Meal>,
    val timing: Timing,
    amount: Double,
    unit: Unit = Unit.UNKNOWN,
    takeUntilFinish: Boolean = false
) : Dosage(amount, unit, takeUntilFinish) {
    val meals = meals.distinct()

    enum class Timing {
        BEFORE, AFTER, DURING
    }

    enum class Meal {
        BREAKFAST, LAUNCH, DINNER, BEFORE_BED
    }
}

class IntervalDosage(
    val everyHours: IntRange,
    amount: Double,
    unit: Unit = Unit.UNKNOWN,
    takeUntilFinish: Boolean = false
)

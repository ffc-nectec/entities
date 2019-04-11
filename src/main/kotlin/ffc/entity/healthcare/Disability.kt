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

import org.joda.time.LocalDate

class Disability(
        val group: Group,
        val detectDate: LocalDate = LocalDate.now(),
        val startDate: LocalDate? = null,
        val cause: Cause = Cause.UNKNOWN,
        val disease: Disease? = null,
        val severity: Severity = Severity.UNDEFINED
) {

    init {
        if (disease != null) {
            require(cause != Cause.UNKNOWN) { "Disability's cause should be known when specified disease" }
        }
        if (startDate != null) {
            require(startDate <= detectDate) { "startDate should before detectDate" }
        }
    }

    enum class Group {
        BLINDNESS, DEAFNESS, MOBILITY, MENTAL, INTELLECTUAL, LEARNING, AUTISM
    }

    enum class Cause {
        UNKNOWN, INBORN, INJURED, DISEASED
    }
}

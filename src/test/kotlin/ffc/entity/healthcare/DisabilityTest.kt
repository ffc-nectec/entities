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

import ffc.entity.healthcare.Disability.Cause.DISEASED
import ffc.entity.healthcare.Disability.Cause.INBORN
import ffc.entity.healthcare.Disability.Group.AUTISM
import ffc.entity.healthcare.Disability.Group.LEARNING
import ffc.entity.healthcare.Disability.Group.MOBILITY
import org.joda.time.LocalDate
import org.junit.Test

class DisabilityTest {

    @Test(expected = IllegalArgumentException::class)
    fun illegalWhenSpecifyDiseaseWithUnknownCause() {
        Disability(MOBILITY, disease = diabetes)
    }

    @Test(expected = IllegalArgumentException::class)
    fun startDateMustNotBeAfterDetectDate() {
        Disability(AUTISM, LocalDate.parse("2018-01-10"), LocalDate.parse("2018-01-11"))
    }

    @Test
    fun specifyDiseaseWithCause() {
        Disability(MOBILITY,
                cause = DISEASED,
                disease = diabetes)
    }

    @Test
    fun create() {
        Disability(LEARNING,
                detectDate = LocalDate.now(),
                startDate = LocalDate.parse("1980-09-20"),
                cause = INBORN,
                severity = Severity.MID)
    }
}

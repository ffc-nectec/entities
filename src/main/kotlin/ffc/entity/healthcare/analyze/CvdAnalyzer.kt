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

package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.Service
import ffc.entity.healthcare.Severity
import ffc.entity.healthcare.SpecialPP

class CvdAnalyzer : Analyzer {

    override val forIssue = HealthIssue.Issue.CVD

    override fun analyzeFrom(service: Service): HealthIssue? {
        return when (service) {
            is SpecialPP -> {
                when (service.ppType.id) {
                    "1B1230" -> HealthProblem(forIssue, service, Severity.OK)
                    "1B1231" -> HealthProblem(forIssue, service, Severity.MID)
                    "1B1232" -> HealthProblem(forIssue, service, Severity.HI)
                    "1B1234", "1B1235" -> HealthProblem(forIssue, service, Severity.VERY_HI)
                    else -> null
                }
            }
            else -> null
        }
    }
}

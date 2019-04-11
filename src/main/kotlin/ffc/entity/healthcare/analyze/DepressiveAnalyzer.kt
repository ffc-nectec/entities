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
import ffc.entity.healthcare.SpecialPP

class DepressiveAnalyzer : Analyzer {

    override val forIssue: HealthIssue.Issue = HealthIssue.Issue.DEPRESSIVE

    override fun analyzeFrom(service: Service): HealthIssue? {
        return if (service is SpecialPP) {
            when (service.ppType.id) {
                "1B0282", "1B0280" -> HealthProblem(forIssue, service, HealthIssue.Severity.OK)
                "1B0281" -> HealthProblem(forIssue, service, HealthIssue.Severity.UNDEFINED)
                "1B0283" -> HealthProblem(forIssue, service, HealthIssue.Severity.LOW)
                "1B0284" -> HealthProblem(forIssue, service, HealthIssue.Severity.MID)
                "1B0285" -> HealthProblem(forIssue, service, HealthIssue.Severity.VERY_HI)
                else -> null
            }
        } else null
    }
}

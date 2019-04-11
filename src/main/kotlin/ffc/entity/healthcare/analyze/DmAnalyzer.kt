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

import ffc.entity.healthcare.HealthCareService
import ffc.entity.healthcare.Icd10
import ffc.entity.healthcare.Service

class DmAnalyzer : Analyzer {

    override val forIssue: HealthIssue.Issue = HealthIssue.Issue.DM

    override fun analyzeFrom(service: Service): HealthIssue? {
        return when (service) {
            is HealthCareService -> {
                val dm = service.diagnosises.firstOrNull {
                    val disease = it.disease
                    if (disease is Icd10)
                        disease.icd10.contains(Regex("^[eE]1[0-4]"))
                    else
                        false
                }
                if (dm != null) HealthChecked(forIssue, service, haveIssue = true) else null
            }
            else -> null
        }
    }
}

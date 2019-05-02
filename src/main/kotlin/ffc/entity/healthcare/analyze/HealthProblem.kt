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

package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.Service
import ffc.entity.healthcare.Severity
import org.joda.time.LocalDate

class HealthProblem(
    issue: Issue,
    val severity: Severity = Severity.UNDEFINED,
    date: LocalDate = LocalDate.now()
) : HealthIssue(issue, date) {

    override val haveIssue: Boolean = severity != Severity.OK

    constructor(
        issue: Issue,
        service: Service,
        severity: Severity = Severity.UNDEFINED
    ) : this(issue, severity, service.time.toLocalDate())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is HealthProblem) return false

        if (issue != other.issue) return false
        if (severity != other.severity) return false

        return true
    }

    override fun hashCode(): Int {
        var result = issue.hashCode()
        result = 31 * result + severity.hashCode()
        return result
    }
}

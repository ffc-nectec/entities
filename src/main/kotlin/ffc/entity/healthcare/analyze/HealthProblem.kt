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

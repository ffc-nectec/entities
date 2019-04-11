package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.Service
import org.joda.time.LocalDate

class HealthChecked(
    issue: HealthIssue.Issue,
    date: LocalDate = LocalDate.now(),
    override val haveIssue: Boolean = false
) : HealthIssue(issue, date) {

    constructor(
        issue: HealthIssue.Issue,
        service: Service,
        haveIssue: Boolean = false
    ) : this(issue, service.time.toLocalDate(), haveIssue)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is HealthChecked) return false

        if (issue != other.issue) return false
        if (haveIssue != other.haveIssue) return false

        return true
    }

    override fun hashCode(): Int {
        var result = issue.hashCode()
        result = 31 * result + haveIssue.hashCode()
        return result
    }
}

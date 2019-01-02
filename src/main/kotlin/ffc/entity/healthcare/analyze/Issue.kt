package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.Service
import org.joda.time.LocalDate

abstract class HealthIssue(
    val issue: Issue,
    val date: LocalDate = LocalDate.now()
) {
    abstract val haveIssue: Boolean
    val type = javaClass.simpleName

    enum class Issue {
        DM, HT, CVD, DEMENTIA, DEPRESSIVE, OA_KNEE,
        FARSIGHTED, NEARSIGHTED, CATARACT, GLAUCOMA, AMD,
        FALL_RISK, ACTIVITIES
    }

    enum class Severity {
        OK, UNDEFINED, LOW, MID, HI, VERY_HI,
    }
}

class HealthProblem(
    issue: HealthIssue.Issue,
    val severity: HealthIssue.Severity = HealthIssue.Severity.UNDEFINED,
    date: LocalDate = LocalDate.now()
) : HealthIssue(issue, date) {

    override val haveIssue: Boolean = severity != Severity.OK

    constructor(
        issue: HealthIssue.Issue,
        service: Service,
        severity: HealthIssue.Severity = HealthIssue.Severity.UNDEFINED
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

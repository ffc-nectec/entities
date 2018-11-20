package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.Service
import org.joda.time.LocalDate

open class HealthIssue(
    val issue: Issue,
    val date: LocalDate = LocalDate.now()
) {

    enum class Issue {
        DM, HT, CVD, DEMENTIA, DEPRESSIVE, OA,
        FARSIGHTED, NEARSIGHTED, CATARACT, GLAUCOMA, AMD
    }

    enum class Severity {
        LOW, MID, HI, VERY_HI, UNDEFINED
    }
}

class HealthProblem(
    issue: HealthIssue.Issue,
    val severity: HealthIssue.Severity = HealthIssue.Severity.UNDEFINED,
    date: LocalDate = LocalDate.now()
) : HealthIssue(issue, date) {

    constructor(
        issue: HealthIssue.Issue,
        service: Service,
        severity: HealthIssue.Severity = HealthIssue.Severity.UNDEFINED
    ) : this(issue, severity, service.time.toLocalDate())
}

class HealthChecked(
    issue: HealthIssue.Issue,
    date: LocalDate = LocalDate.now()
) : HealthIssue(issue, date) {

    constructor(
        issue: HealthIssue.Issue,
        service: Service
    ) : this(issue, service.time.toLocalDate())
}

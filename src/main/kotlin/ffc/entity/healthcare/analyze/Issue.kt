package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.Service
import org.joda.time.LocalDate

data class HealthIssue(
    val issue: Issue,
    val severity: Severity = Severity.UNDEFINE,
    val date: LocalDate = LocalDate.now()
) {
    constructor(
        issue: Issue,
        severity: Severity = Severity.UNDEFINE,
        service: Service
    ) : this(issue, severity, service.time.toLocalDate())

    enum class Issue {
        DM, HT, CVD, DEMENTIA, DEPRESSIVE
    }

    enum class Severity {
        LOW, MID, HI, VERY_HI, UNDEFINE
    }
}

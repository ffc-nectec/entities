package ffc.entity.healthcare.analyze

data class HealthIssue(
    val issue: Issue,
    val severity: Severity = Severity.UNDEFINE
) {
    enum class Issue {
        DM, HT, CVD, DEMENTIA, DEPRESSIVE
    }

    enum class Severity {
        LOW, MID, HI, VERY_HI, UNDEFINE
    }
}

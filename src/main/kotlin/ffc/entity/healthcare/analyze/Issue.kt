package ffc.entity.healthcare.analyze

data class HealthIssue(
    val issue: Issue,
    val severity: Severity? = null
) {
    enum class Issue {
        DM, HT, CVD, DEMENTIA,
    }

    enum class Severity {
        LOW, MID, HI, VERY_HI
    }
}

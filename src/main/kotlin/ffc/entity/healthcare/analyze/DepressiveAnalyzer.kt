package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.Service
import ffc.entity.healthcare.SpecialPP

class DepressiveAnalyzer : Analyzer {

    override val forIssue: HealthIssue.Issue = HealthIssue.Issue.DEPRESSIVE

    override fun analyzeFrom(service: Service): HealthIssue? {
        return if (service is SpecialPP) {
            val severity = when (service.ppType.id) {
                "1B0285" -> HealthIssue.Severity.VERY_HI
                "1B0284" -> HealthIssue.Severity.MID
                "1B0283" -> HealthIssue.Severity.LOW
                "1B0281" -> HealthIssue.Severity.UNDEFINE
                else -> null
            }
            if (severity != null) HealthIssue(forIssue, severity, service) else null
        } else null
    }
}

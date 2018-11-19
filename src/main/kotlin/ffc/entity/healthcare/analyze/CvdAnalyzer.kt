package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.Services
import ffc.entity.healthcare.SpecialPP

class CvdAnalyzer : Analyzer {

    override val forIssue = HealthIssue.Issue.CVD

    override fun analyzeFrom(service: Services): HealthIssue? {
        return when (service) {
            is SpecialPP -> {
                val severity = when (service.ppType.id) {
                    "1B1231" -> HealthIssue.Severity.MID
                    "1B1232" -> HealthIssue.Severity.HI
                    "1B1234", "1B1235" -> HealthIssue.Severity.VERY_HI
                    else -> null
                }
                if (severity != null) HealthIssue(forIssue, severity) else null
            }
            else -> null
        }
    }
}

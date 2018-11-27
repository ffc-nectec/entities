package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.Service
import ffc.entity.healthcare.SpecialPP

class DepressiveAnalyzer : Analyzer {

    override val forIssue: HealthIssue.Issue = HealthIssue.Issue.DEPRESSIVE

    override fun analyzeFrom(service: Service): HealthIssue? {
        return if (service is SpecialPP) {
            when (service.ppType.id) {
                "1B0282", "1B0280" -> HealthProblem(forIssue, service, HealthIssue.Severity.OK)
                "1B0281" -> HealthProblem(forIssue, service, HealthIssue.Severity.UNDEFINED)
                "1B0283" -> HealthProblem(forIssue, service, HealthIssue.Severity.LOW)
                "1B0284" -> HealthProblem(forIssue, service, HealthIssue.Severity.MID)
                "1B0285" -> HealthProblem(forIssue, service, HealthIssue.Severity.VERY_HI)
                else -> null
            }
        } else null
    }
}

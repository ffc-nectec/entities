package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.Service
import ffc.entity.healthcare.SpecialPP

class ADLAnalyzer : Analyzer {

    override val forIssue: HealthIssue.Issue = HealthIssue.Issue.ACTIVITIES

    override fun analyzeFrom(service: Service): HealthIssue? {
        return if (service is SpecialPP) {
            when (service.ppType.id) {
                "1B1280" -> HealthProblem(forIssue, service, HealthIssue.Severity.OK)
                "1B1281" -> HealthProblem(forIssue, service, HealthIssue.Severity.MID)
                "1B1282" -> HealthProblem(forIssue, service, HealthIssue.Severity.VERY_HI)
                else -> null
            }
        } else null
    }
}

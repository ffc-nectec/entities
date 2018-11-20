package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.Service
import ffc.entity.healthcare.SpecialPP

class FallRiskAnalyzer : Analyzer {

    override val forIssue = HealthIssue.Issue.FALL_RISK

    override fun analyzeFrom(service: Service): HealthIssue? {
        return if (service is SpecialPP) {
            when (service.ppType.id) {
                "1B1200" -> HealthChecked(forIssue, service)
                "1B1201", "1B1202" -> HealthProblem(forIssue, service)
                else -> null
            }
        } else null
    }
}

package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.Service
import ffc.entity.healthcare.SpecialPP

class OaAnalyzer : Analyzer {

    override val forIssue = HealthIssue.Issue.OA

    override fun analyzeFrom(service: Service): HealthIssue? {
        return if (service is SpecialPP) {
            when (service.ppType.id) {
                "1B1270" -> HealthChecked(forIssue, service)
                "1B1271", "1B1272" -> HealthProblem(forIssue, service)
                else -> null
            }
        } else null
    }
}

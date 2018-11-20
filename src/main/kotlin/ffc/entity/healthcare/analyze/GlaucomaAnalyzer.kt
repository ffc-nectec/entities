package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.Service
import ffc.entity.healthcare.SpecialPP

class GlaucomaAnalyzer : Analyzer {

    override val forIssue = HealthIssue.Issue.GLAUCOMA

    override fun analyzeFrom(service: Service): HealthIssue? {
        return if (service is SpecialPP) {
            when (service.ppType.id) {
                "1B1253" -> HealthChecked(forIssue, service)
                "1B1254", "1B1255" -> HealthProblem(forIssue, service)
                else -> null
            }
        } else null
    }
}

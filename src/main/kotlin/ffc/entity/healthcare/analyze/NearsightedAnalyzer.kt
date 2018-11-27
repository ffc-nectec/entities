package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.Service
import ffc.entity.healthcare.SpecialPP

class NearsightedAnalyzer : Analyzer {

    override val forIssue = HealthIssue.Issue.NEARSIGHTED

    override fun analyzeFrom(service: Service): HealthIssue? {
        return if (service is SpecialPP) {
            when (service.ppType.id) {
                "1B1240" -> HealthChecked(forIssue, service)
                "1B1241", "1B1242" -> HealthChecked(forIssue, service, haveIssue = true)
                else -> null
            }
        } else null
    }
}

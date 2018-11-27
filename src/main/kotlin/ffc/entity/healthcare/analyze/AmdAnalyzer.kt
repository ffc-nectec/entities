package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.Service
import ffc.entity.healthcare.SpecialPP

class AmdAnalyzer : Analyzer {

    override val forIssue = HealthIssue.Issue.AMD

    override fun analyzeFrom(service: Service): HealthIssue? {
        return if (service is SpecialPP) {
            when (service.ppType.id) {
                "1B1256" -> HealthChecked(forIssue, service)
                "1B1257", "1B1258" -> HealthChecked(forIssue, service, haveIssue = true)
                else -> null
            }
        } else null
    }
}

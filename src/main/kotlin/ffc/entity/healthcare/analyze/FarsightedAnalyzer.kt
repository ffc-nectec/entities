package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.Service
import ffc.entity.healthcare.SpecialPP

class FarsightedAnalyzer : Analyzer {

    override val forIssue = HealthIssue.Issue.FARSIGHTED

    override fun analyzeFrom(service: Service): HealthIssue? {
        return if (service is SpecialPP) {
            when (service.ppType.id) {
                "1B1243" -> HealthChecked(forIssue, service)
                "1B1244", "1B1245" -> HealthChecked(forIssue, service, haveIssue = true)
                else -> null
            }
        } else null
    }
}

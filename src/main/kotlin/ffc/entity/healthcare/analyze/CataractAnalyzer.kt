package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.Service
import ffc.entity.healthcare.SpecialPP

class CataractAnalyzer : Analyzer {

    override val forIssue = HealthIssue.Issue.CATARACT

    override fun analyzeFrom(service: Service): HealthIssue? {
        return if (service is SpecialPP) {
            when (service.ppType.id) {
                "1B1250" -> HealthChecked(forIssue, service)
                "1B1251", "1B1252" -> HealthChecked(forIssue, service, haveIssue = true)
                else -> null
            }
        } else null
    }
}

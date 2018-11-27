package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.Service
import ffc.entity.healthcare.SpecialPP

class DementiaAnalyzer : Analyzer {

    override val forIssue = HealthIssue.Issue.DEMENTIA

    override fun analyzeFrom(service: Service): HealthIssue? {
        return if (service is SpecialPP) {
            when (service.ppType.id) {
                "1B1220" -> HealthChecked(forIssue, service)
                "1B1221", "1B1223" -> HealthChecked(forIssue, service, haveIssue = true) // AMT
                "1B1226", "1B1225" -> HealthChecked(forIssue, service, haveIssue = true) // MMSE-T 2002
                else -> null
            }
        } else null
    }
}

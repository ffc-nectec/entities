package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.Services
import ffc.entity.healthcare.SpecialPP

class DementiaAnalyzer : Analyzer {
    override val forIssue = HealthIssue.Issue.DEMENTIA

    override fun analyzeFrom(service: Services): HealthIssue? {
        return if (service is SpecialPP) {
            when (service.ppType.id) {
                //"1B1220" -> HealthIssue(forIssue)
                "1B1221", "1B1223" -> HealthIssue(forIssue) // AMT
                "1B1226", "1B1225" -> HealthIssue(forIssue) // MMSE-T 2002
                else -> null
            }
        } else null
    }
}

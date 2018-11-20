package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.Service
import ffc.entity.healthcare.SpecialPP

class CvdAnalyzer : Analyzer {

    override val forIssue = HealthIssue.Issue.CVD

    override fun analyzeFrom(service: Service): HealthIssue? {
        return when (service) {
            is SpecialPP -> {
                when (service.ppType.id) {
                    "1B1230" -> HealthChecked(forIssue, service)
                    "1B1231" -> HealthProblem(forIssue, service, HealthIssue.Severity.MID)
                    "1B1232" -> HealthProblem(forIssue, service, HealthIssue.Severity.HI)
                    "1B1234", "1B1235" -> HealthProblem(forIssue, service, HealthIssue.Severity.VERY_HI)
                    else -> null
                }
            }
            else -> null
        }
    }
}

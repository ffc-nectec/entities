package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.HealthCareService
import ffc.entity.healthcare.NCDScreen
import ffc.entity.healthcare.Service
import ffc.entity.healthcare.bloodPressureLevel

class HtAnalyzer : Analyzer {

    override val forIssue: HealthIssue.Issue = HealthIssue.Issue.HT

    override fun analyzeFrom(service: Service): HealthIssue? {
        val severity = when (service) {
            is NCDScreen -> {
                val bpLevel = service.bloodPressureLevel
                when {
                    bpLevel == null -> null
                    bpLevel.isHigh -> HealthIssue.Severity.HI
                    bpLevel.isPreHigh -> HealthIssue.Severity.MID
                    else -> HealthIssue.Severity.LOW
                }
            }
            is HealthCareService -> {
                val bpLevel = service.bloodPressureLevel
                when {
                    bpLevel == null -> null
                    bpLevel.isHigh -> HealthIssue.Severity.HI
                    bpLevel.isPreHigh -> HealthIssue.Severity.MID
                    else -> HealthIssue.Severity.LOW
                }
            }
            else -> null
        }
        return if (severity != null) HealthProblem(forIssue, service, severity) else null
    }
}

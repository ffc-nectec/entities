package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.HealthCareService
import ffc.entity.healthcare.NCDScreen
import ffc.entity.healthcare.Services
import ffc.entity.healthcare.bloodPressureLevel

class HtAnalyzer : Analyzer {

    override val forIssue: HealthIssue.Issue = HealthIssue.Issue.HT

    override fun analyzeFrom(service: Services): HealthIssue? {
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
        return if (severity != null) HealthIssue(forIssue, severity) else null
    }
}

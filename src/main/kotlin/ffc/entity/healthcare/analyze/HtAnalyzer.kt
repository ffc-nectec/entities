package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.BloodPressureAnalyzer
import ffc.entity.healthcare.HealthCareService
import ffc.entity.healthcare.Icd10
import ffc.entity.healthcare.NCDScreen
import ffc.entity.healthcare.Service
import ffc.entity.healthcare.bloodPressureLevel

class HtAnalyzer : Analyzer {

    override val forIssue: HealthIssue.Issue = HealthIssue.Issue.HT

    override fun analyzeFrom(service: Service): HealthIssue? {
        val severity = when (service) {
            is NCDScreen -> service.bloodPressureLevel.severity
            is HealthCareService -> service.bloodPressureLevel.severity
            else -> null
        }

        if (severity == null && service is HealthCareService) {
            val ht = service.diagnosises.firstOrNull {
                val disease = it.disease
                if (disease is Icd10)
                    disease.icd10.contains(Regex("^[iI]1[0-5]"))
                else
                    false
            }?.let {
                return HealthChecked(forIssue, service, haveIssue = true)
            }
        }
        return if (severity != null) HealthProblem(forIssue, service, severity) else null
    }

    val BloodPressureAnalyzer?.severity: HealthIssue.Severity?
        get() {
            return when {
                this == null -> null
                isPreHigh -> HealthIssue.Severity.MID
                isHigh -> HealthIssue.Severity.HI
                else -> null
            }
        }
}

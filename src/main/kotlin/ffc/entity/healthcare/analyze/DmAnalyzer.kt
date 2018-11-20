package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.HealthCareService
import ffc.entity.healthcare.Icd10
import ffc.entity.healthcare.Service

class DmAnalyzer : Analyzer {

    override val forIssue: HealthIssue.Issue = HealthIssue.Issue.DM

    override fun analyzeFrom(service: Service): HealthIssue? {
        return when (service) {
            is HealthCareService -> {
                val dm = service.diagnosises.firstOrNull {
                    val disease = it.disease
                    if (disease is Icd10)
                        disease.icd10.contains(Regex("^[eE]1[0-4]"))
                    else
                        false
                }
                if (dm != null) HealthProblem(forIssue, service) else null
            }
            else -> null
        }
    }
}

package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.HealthCareService
import ffc.entity.healthcare.Service

class HealthAnalyzer {

    val analyzers = mutableListOf(
            HtAnalyzer(),
            DmAnalyzer(),
            CvdAnalyzer(),
            DementiaAnalyzer(),
            DepressiveAnalyzer()
    )

    val result = mutableMapOf<HealthIssue.Issue, HealthIssue>()

    fun analyze(vararg services: Service) {
        services.forEach { analyzeIt(it) }
    }

    private fun analyzeIt(service: Service) {
        analyzers.forEach { analyzer ->
            val res = analyzer.analyzeFrom(service)
            res?.let { result.put(analyzer.forIssue, it) }
        }
        if (service is HealthCareService) {
            if (service.specialPPs.isNotEmpty()) {
                analyze(*service.specialPPs.toTypedArray())
            }
            service.ncdScreen?.let { analyzeIt(it) }
        }
    }

}

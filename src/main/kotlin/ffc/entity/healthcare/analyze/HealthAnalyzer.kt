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

    val problems: Map<HealthIssue.Issue, HealthProblem>
        get() = result.filterValueType()

    val checked: Map<HealthIssue.Issue, HealthChecked>
        get() = result.filterValueType()

    fun analyze(vararg services: Service) {
        services.sortBy { it.time }
        services.forEach { analyzeIt(it) }
    }

    private fun analyzeIt(service: Service) {
        val activatedAnalyzer = mutableListOf<Analyzer>()
        analyzers.forEach { analyzer ->
            val res = analyzer.analyzeFrom(service)
            res?.let {
                result.put(analyzer.forIssue, it)
                activatedAnalyzer.add(analyzer)
            }
        }
        analyzers.removeAll(activatedAnalyzer)

        if (service is HealthCareService) {
            if (service.specialPPs.isNotEmpty()) {
                analyze(*service.specialPPs.toTypedArray())
            }
            service.ncdScreen?.let { analyzeIt(it) }
        }
    }

    private inline fun <K, V, reified R> Map<out K, V>.filterValueType(): Map<K, R> {
        return filterValues { it is R }
                .mapValues { it.value as R }
    }
}

package ffc.entity.healthcare.analyze

import com.google.gson.annotations.SerializedName
import ffc.entity.gson.JsonExclude
import ffc.entity.healthcare.HealthCareService
import ffc.entity.healthcare.Service
import org.joda.time.DateTime

class HealthAnalyzer {

    @JsonExclude
    val analyzers = mutableListOf(
        HtAnalyzer(),
        DmAnalyzer(),
        CvdAnalyzer(),
        DementiaAnalyzer(),
        DepressiveAnalyzer(),
        OaKneeAnalyzer(),
        NearsightedAnalyzer(),
        FarsightedAnalyzer(),
        AmdAnalyzer(),
        CataractAnalyzer(),
        GlaucomaAnalyzer(),
        NearsightedAnalyzer(),
        FallRiskAnalyzer(),
        ADLAnalyzer()
    )

    val result: Map<HealthIssue.Issue, HealthIssue>
        get() = _result

    @SerializedName("result")
    private val _result = mutableMapOf<HealthIssue.Issue, HealthIssue>()

    val lastAnalyzer = DateTime()

    val problems: Map<HealthIssue.Issue, HealthProblem>
        get() = _result.filterValueType()

    val checked: Map<HealthIssue.Issue, HealthChecked>
        get() = _result.filterValueType()

    fun analyze(vararg services: Service) {
        services.sortBy { it.time }
        services.forEach { analyzeIt(it) }
    }

    private fun analyzeIt(service: Service) {
        val activatedAnalyzer = mutableListOf<Analyzer>()
        analyzers.forEach { analyzer ->
            val res = analyzer.analyzeFrom(service)
            res?.let {
                _result.put(analyzer.forIssue, it)
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

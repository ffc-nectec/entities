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

    var timestamp: DateTime? = DateTime.now()
        private set

    fun analyze(vararg services: Service) {
        timestamp = DateTime.now()
        services.sortByDescending { it.time }
        services.forEach { analyzeIt(it) }
    }

    fun haveProblemWith(issue: HealthIssue.Issue): Boolean {
        return _result[issue]?.haveIssue == true
    }

    fun haveProblemWith(issue: HealthIssue.Issue, severity: HealthIssue.Severity): Boolean {
        return _result.values.contains(HealthProblem(issue, severity))
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
}

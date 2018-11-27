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

    val issue: Map<HealthIssue.Issue, HealthIssue>
        get() {
            val issue = _result.values.filter {
                when (it) {
                    is HealthProblem -> it.severity != HealthIssue.Severity.OK
                    is HealthChecked -> it.haveIssue
                    else -> false
                }
            }
            _result.mapKeys { }
            val map = mutableMapOf<HealthIssue.Issue, HealthIssue>()
            issue.forEach {
                map[it.issue] = it
            }
            return map
        }

    fun analyze(vararg services: Service) {
        timestamp = DateTime.now()
        services.sortByDescending { it.time }
        services.forEach { analyzeIt(it) }
    }

    fun haveProblemWith(issue: HealthIssue.Issue): Boolean {
        val value = _result[issue]
        return when (value) {
            is HealthProblem -> value.severity != HealthIssue.Severity.OK
            is HealthChecked -> value.haveIssue
            else -> false
        }
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

package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.Services

class HealthAnalyzer {

    val analyzers = mutableListOf(HtAnalyzer(), DmAnalyzer())

    val result = mutableMapOf<HealthIssue.Issue, HealthIssue>()

    fun analyze(vararg service: Services) {
        analyzers.forEach { analyzer ->
            service.forEach { service ->
                val res = analyzer.analyzeFrom(service)
                res?.let { result.put(analyzer.forIssue, it) }
            }
        }
    }

}

package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.HealthCareService
import ffc.entity.healthcare.Services
import ffc.entity.healthcare.bloodPressureLevel

class HealthAnalyzer {

    val analyzers = mutableListOf(HtAnalyzer())

    val result = mutableMapOf<HealthIssue, Serverity>()

    fun analyze(vararg service: Services) {
        analyzers.forEach { analyzer ->
            service.forEach { service ->
                val res = analyzer.analyzeFrom(service)
                res?.let { result.put(analyzer.forIssue, it) }
            }
        }
    }

    enum class HealthIssue {
        DM, HT
    }

    enum class Serverity {
        LOW, MID, HI
    }

    interface Analyzer {

        val forIssue: HealthIssue

        fun analyzeFrom(service: Services): Serverity?
    }

    class HtAnalyzer : Analyzer {

        override val forIssue: HealthIssue
            get() = HealthIssue.HT

        override fun analyzeFrom(service: Services): Serverity? {
            return when (service) {
                is HealthCareService -> {
                    val bpLevel = service.bloodPressureLevel
                    when {
                        bpLevel == null -> null
                        bpLevel.isHigh -> Serverity.HI
                        bpLevel.isPreHigh -> Serverity.MID
                        else -> Serverity.LOW
                    }
                }
                else -> null
            }
        }
    }
}

package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.Services

interface Analyzer {

    val forIssue: HealthIssue.Issue

    fun analyzeFrom(service: Services): HealthIssue?
}

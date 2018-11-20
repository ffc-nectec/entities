package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.Service

interface Analyzer {

    val forIssue: HealthIssue.Issue

    fun analyzeFrom(service: Service): HealthIssue?
}

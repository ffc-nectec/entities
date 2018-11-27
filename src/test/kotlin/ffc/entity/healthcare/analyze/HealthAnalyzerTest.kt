package ffc.entity.healthcare.analyze

import ffc.entity.gson.toJson
import ffc.entity.healthcare.BloodPressure
import ffc.entity.healthcare.Diagnosis
import ffc.entity.healthcare.Frequency
import ffc.entity.healthcare.HealthCareService
import ffc.entity.healthcare.NCDScreen
import ffc.entity.healthcare.cvdHiRiskSpecialPP
import ffc.entity.healthcare.cvdSpecialPP
import ffc.entity.healthcare.dementiaCheckSpecialPP
import ffc.entity.healthcare.diabetes
import ffc.entity.healthcare.hypertension
import ffc.entity.healthcare.patient
import ffc.entity.healthcare.provider
import me.piruin.geok.geometry.Point
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should equal`
import org.amshove.kluent.`should have key`
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.junit.Test

class HealthAnalyzerTest {

    val analyzer = HealthAnalyzer()

    private val visit1 = HealthCareService(provider.id, patient.id).apply {
        time = DateTime.now()
        syntom = "ปกติ"
        weight = 61.5
        height = 170.0
        bloodPressure = BloodPressure(145.0, 95.0)
        principleDx = hypertension
        diagnosises.add(Diagnosis(diabetes, Diagnosis.Type.CO_MORBIDITY))
        respiratoryRate = 24.0
        pulseRate = 72.0
        bodyTemperature = 37.5
        location = Point(14.192390, 120.029384)
        syntom = "ทานอาหารได้น้อย เบื่ออาหาร"
        nextAppoint = LocalDate.parse("2019-09-21")
        ncdScreen = NCDScreen(providerId, patientId,
                bloodSugar = 150.0, // mg/dL
                hasHtInFamily = true,
                alcohol = Frequency.RARELY,
                smoke = Frequency.USUALLY)
        addSpecialPP(cvdSpecialPP)
    }

    private val visit2 = HealthCareService(provider.id, patient.id).apply {
        time = DateTime.now().plusMonths(3)
        principleDx = diabetes
        syntom = "ทานอาหารได้น้อย เบื่ออาหาร"
        addSpecialPP(cvdHiRiskSpecialPP)
        addSpecialPP(dementiaCheckSpecialPP)
    }

    @Test
    fun visit() {
        analyzer.analyze(visit1)

        with(analyzer.result) {
            size `should be equal to` 3
            getAs<HealthProblem>(HealthIssue.Issue.HT)!!.severity `should equal` HealthIssue.Severity.HI
            getAs<HealthChecked>(HealthIssue.Issue.DM)!!.haveIssue `should be` true
            getAs<HealthProblem>(HealthIssue.Issue.CVD)!!.severity `should equal` HealthIssue.Severity.MID

        }
    }

    @Test
    fun mutipleVisit() {
        analyzer.analyze(visit2, visit1)

        with(analyzer.result) {
            size `should be equal to` 4
            `should have key`(HealthIssue.Issue.HT)
            `should have key`(HealthIssue.Issue.DM)
            `should have key`(HealthIssue.Issue.CVD)
            `should have key`(HealthIssue.Issue.DEMENTIA)

            getAs<HealthChecked>(HealthIssue.Issue.DEMENTIA)!!.haveIssue `should equal` false
            getAs<HealthProblem>(HealthIssue.Issue.CVD)!!.severity `should equal` HealthIssue.Severity.VERY_HI
        }

    }

    @Test
    fun mutipleVisitUnsorted() {
        analyzer.analyze(visit1, visit2)

        with(analyzer.result) {
            size `should be equal to` 4

            getAs<HealthProblem>(HealthIssue.Issue.CVD)!!.severity `should equal` HealthIssue.Severity.VERY_HI
        }
    }

    @Test
    fun name() {
        analyzer.analyze(visit1)

        println(analyzer.toJson())
    }
}

@Suppress("UNCHECKED_CAST")
inline fun <T : HealthIssue> Map<HealthIssue.Issue, HealthIssue>.getAs(key: HealthIssue.Issue): T? = get(key) as T?

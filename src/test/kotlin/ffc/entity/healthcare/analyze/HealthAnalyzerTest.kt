package ffc.entity.healthcare.analyze

import ffc.entity.healthcare.BloodPressure
import ffc.entity.healthcare.Diagnosis
import ffc.entity.healthcare.Frequency
import ffc.entity.healthcare.HealthCareService
import ffc.entity.healthcare.NCDScreen
import ffc.entity.healthcare.cvdSpecialPP
import ffc.entity.healthcare.diabetes
import ffc.entity.healthcare.hypertension
import ffc.entity.healthcare.patient
import ffc.entity.healthcare.provider
import me.piruin.geok.geometry.Point
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should equal`
import org.joda.time.LocalDate
import org.junit.Test

class HealthAnalyzerTest {

    @Test
    fun ht() {
        val visit = HealthCareService(provider.id, patient.id).apply {
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

        val analyzer = HealthAnalyzer()

        analyzer.analyze(visit)

        with(analyzer.result) {
            size `should be equal to` 3
            get(HealthIssue.Issue.HT)!!.severity `should equal` HealthIssue.Severity.HI
            get(HealthIssue.Issue.DM)!!.severity `should equal` null
            get(HealthIssue.Issue.CVD)!!.severity `should equal` HealthIssue.Severity.MID
        }

    }
}

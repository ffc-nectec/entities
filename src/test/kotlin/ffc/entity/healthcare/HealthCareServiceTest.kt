package ffc.entity.healthcare

import ffc.entity.gson.toJson
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should equal`
import org.junit.Test

class HealthCareServiceTest {

    val comServType = CommunityServiceType(
            "1B0030",
            "ตรวจคัดกรองความเสี่ยง/โรคมะเร็งเต้านมได้ผลปกติ ผู้รับบริการเคยตรวจด้วยตนเองได้ผลปกติ"
    )

    val hypertension = Disease("Hypertension", "i10").apply {
        isChronic = true
        isNonCommunicable = true
    }

    @Test
    fun visit() {
        var visit = HomeVisit(comServType).apply {
            syntom = "ปกติ"
            weight = 61.5
            height = 170.0
            bloodPressure = ThaiBloodPressure(145.0, 95.0)
            diagnosises.add(Diagnosis(hypertension, Diagnosis.Type.PRINCIPLE_DX))
            respiratoryRate = 192
            syntom = "ทานอาหารได้น้อย เบื่ออาหาร"
            detail = "ตรวจร่างกายทั่วไป / อธิบานผลเสียของโรค / เปิดโอกาสให้ผู้ป่วยซักถาม"
            result = "ผู้ป่วยเข้าใจเกี่ยวกับโรค สามารถดูแลตัวเองได้และปฎิบัติตามคำแนะนำได้ดี"
            plan = "ติดตามเยี่ยมปีละ 1 ครั้ง"
        }

        with(visit) {
            bmi!!.isNormal `should be` true
            bmi!!.value `should equal` 21.3
            bloodPressure!!.isHigh `should be` true
        }

        print(visit.toJson())
    }
}

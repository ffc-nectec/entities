package ffc.entity.healthcare

import ffc.entity.Lang
import ffc.entity.Person
import ffc.entity.ThaiCitizenId
import ffc.entity.User
import ffc.entity.gson.toJson
import ffc.entity.util.generateTempId
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should equal`
import org.junit.Test

class HealthCareServiceTest {

    val comServType = CommunityServiceType(
            "1B0030",
            "ตรวจคัดกรองความเสี่ยง/โรคมะเร็งเต้านมได้ผลปกติ ผู้รับบริการเคยตรวจด้วยตนเองได้ผลปกติ"
    )

    val hypertension = Disease(
            "id2h3",
            "Hypertension",
            "i10",
            isEpimedic = false,
            isChronic = true,
            isNCD = true).apply {
        translation.put(Lang.th, "ความดันโลหิต")
    }

    val provider = User(generateTempId(),
            "blast",
            "123456",
            User.Role.PROVIDER,
            User.Role.ADMIN)

    val patient = Person().apply {
        identities.add(ThaiCitizenId("1154785400590"))
        prename = "Mr."
        firstname = "Piruin"
        lastname = "Panichphol"
    }

    @Test
    fun visit() {
        val visit = provider.homeVisit(patient.id, comServType).apply {
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
            serviceType `should equal` comServType
        }

        print(visit.toJson())
    }
}

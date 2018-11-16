package ffc.entity.healthcare

import ffc.entity.Lang
import ffc.entity.Person
import ffc.entity.ThaiCitizenId
import ffc.entity.User
import ffc.entity.gson.parseTo
import ffc.entity.gson.toJson
import ffc.entity.resourceFile
import ffc.entity.util.URLs
import ffc.entity.util.generateTempId
import me.piruin.geok.geometry.Point
import org.amshove.kluent.`should be instance of`
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should contain all`
import org.amshove.kluent.`should contain`
import org.amshove.kluent.`should equal`
import org.joda.time.LocalDate
import org.junit.Test

class HealthCareServiceTest {

    val visitHT = CommunityService.ServiceType(
            "1A000",
            "เยี่ยมผู้ป่วยโรคความดันโลหิตสูง"
    )

    val hypertension = Icd10(
            "Hypertension",
            "i10",
            isEpimedic = false,
            isChronic = true,
            isNCD = true
    ).apply {
        translation.put(Lang.th, "ความดันโลหิตสูง")
    }

    val screeningPP = SpecialPP.PPType(
            "1B1232",
            "การตรวจคัดกรองความเสี่ยงโรคหัวใจและหลอดเลือดสมองในผู้สูงอายุ พบว่ามีความเสี่ยงสูง"
    )

    val provider = User(
            generateTempId(),
            "blast",
            "123456",
            User.Role.PROVIDER,
            User.Role.ADMIN
    )

    val patient = Person().apply {
        identities.add(ThaiCitizenId("1154785400590"))
        prename = "Mr."
        firstname = "Piruin"
        lastname = "Panichphol"
    }

    @Test
    fun visit() {
        val visit = HealthCareService(provider.id, patient.id).apply {
            syntom = "ปกติ"
            weight = 61.5
            height = 170.0
            bloodPressure = BloodPressure(145.0, 95.0)
            principleDx = hypertension
            respiratoryRate = 24.0
            pulseRate = 72.0
            bodyTemperature = 37.5
            location = Point(14.192390, 120.029384)
            syntom = "ทานอาหารได้น้อย เบื่ออาหาร"
            nextAppoint = LocalDate.parse("2019-09-21")
            photosUrl = URLs(
                    "http://ffc.in.th/img/ffcc.jpg",
                    "http://ffc.in.th/img/ffcc1.jpg"
            )
            val homeVisit = HomeVisit(visitHT,
                    detail = "ตรวจร่างกายทั่วไป / อธิบานผลเสียของโรค / เปิดโอกาสให้ผู้ป่วยซักถาม",
                    result = "ผู้ป่วยเข้าใจเกี่ยวกับโรค สามารถดูแลตัวเองได้และปฎิบัติตามคำแนะนำได้ดี",
                    plan = "ติดตามเยี่ยมปีละ 1 ครั้ง"
            )
            communityServices.add(homeVisit)
            addSpecialPP(screeningPP)
        }

        with(visit) {
            providerId `should equal` provider.id
            patientId `should equal` patient.id
            bmi!!.isNormal `should be` true
            bmi!!.value `should equal` 21.3
            bloodPressureLevel!!.isHigh `should be` true
            communityServices[0].serviceType `should equal` visitHT
            diagnosises `should contain` Diagnosis(hypertension, Diagnosis.Type.PRINCIPLE_DX)
        }
        print(visit.toJson())
    }

    @Test
    fun fromJson() {
        val visit = resourceFile("HomeVisit.json").parseTo<HealthCareService>()
        with(visit) {
            pulseRate `should equal` 72.0
            respiratoryRate `should equal` 24.0
            photosUrl `should contain all` listOf(
                    "http://ffc.in.th/img/ffcc.jpg",
                    "http://ffc.in.th/img/ffcc1.jpg")
            communityServices[0] `should be instance of` HomeVisit::class.java
            communityServices[0].serviceType `should equal` visitHT
            specialPPs[0].ppType `should equal` screeningPP
        }
    }

    @Test
    fun toJsonFromJson() {
        val visitJson = HealthCareService(provider.id, patient.id).apply {
            syntom = "ปกติ"
            weight = 61.5
            height = 170.0
            bloodPressure = BloodPressure(145.0, 95.0)
            principleDx = hypertension
            respiratoryRate = 24.0
            pulseRate = 72.0
            bodyTemperature = 37.5
            location = Point(14.192390, 120.029384)
            syntom = "ทานอาหารได้น้อย เบื่ออาหาร"
            nextAppoint = LocalDate.parse("2019-09-21")
            val homeVisit = HomeVisit(visitHT,
                    detail = "ตรวจร่างกายทั่วไป / อธิบานผลเสียของโรค / เปิดโอกาสให้ผู้ป่วยซักถาม",
                    result = "ผู้ป่วยเข้าใจเกี่ยวกับโรค สามารถดูแลตัวเองได้และปฎิบัติตามคำแนะนำได้ดี",
                    plan = "ติดตามเยี่ยมปีละ 1 ครั้ง")
            communityServices.add(homeVisit)
        }.toJson()

        print(visitJson)

        val visitFromJson = visitJson.parseTo<HealthCareService>()

        with(visitFromJson) {
            pulseRate `should equal` 72.0
            nextAppoint `should equal` LocalDate.parse("2019-09-21")
            principleDx `should equal` hypertension
        }
    }
}

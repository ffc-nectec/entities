package ffc.entity.healthcare

import ffc.entity.Lang
import ffc.entity.Person
import ffc.entity.ThaiCitizenId
import ffc.entity.User
import ffc.entity.util.generateTempId

internal val visitHT = CommunityService.ServiceType(
        "1A000",
        "เยี่ยมผู้ป่วยโรคความดันโลหิตสูง"
)

internal val cvdSpecialPP = SpecialPP.PPType(
        "1B1231",
        "การตรวจคัดกรองความเสี่ยงโรคหัวใจและหลอดเลือดสมองในผู้สูงอายุ พบว่ามีความเสี่ยง")

internal val cvdHiRiskSpecialPP = SpecialPP.PPType(
        "1B1235",
        "การตรวจคัดกรองความเสี่ยงโรคหัวใจและหลอดเลือดสมองในผู้สูงอายุพบว่ามีความเสี่ยงสูงมาก รักษาต่อ")

internal val hypertension = Icd10(
        "Hypertension",
        "i10",
        isEpimedic = false,
        isChronic = true,
        isNCD = true
).apply {
    translation.put(Lang.th, "ความดันโลหิตสูง")
}

internal val diabetes = Icd10(
        "Diabetes mellitus (DM)",
        "E10.3",
        isChronic = true,
        isNCD = true
)

internal val screeningPP = SpecialPP.PPType(
        "1B1232",
        "การตรวจคัดกรองความเสี่ยงโรคหัวใจและหลอดเลือดสมองในผู้สูงอายุ พบว่ามีความเสี่ยงสูง"
)

internal val provider = User(
        generateTempId(),
        "blast",
        "123456",
        User.Role.PROVIDER,
        User.Role.ADMIN
)

internal val patient = Person().apply {
    identities.add(ThaiCitizenId("1154785400590"))
    prename = "Mr."
    firstname = "Piruin"
    lastname = "Panichphol"
}

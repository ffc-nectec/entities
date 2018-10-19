package ffc.entity.place

enum class ClassSchool {
    PreKindergarten,
    Kindergarten,
    PrimarySchool,
    JuniorHighSchool,
    HighSchool
}

fun ClassSchool.valueOfTh(th: String): ClassSchool {
    return when {
        th.contains("ประถม") -> ClassSchool.PrimarySchool
        th.contains("มัธยม") -> ClassSchool.HighSchool
        th.contains("เตรียมอนุบาล") -> ClassSchool.PreKindergarten
        th.contains("ก่อนอนุบาล") -> ClassSchool.PreKindergarten
        th.contains("อนุบาล") -> ClassSchool.Kindergarten
        else -> this
    }
}

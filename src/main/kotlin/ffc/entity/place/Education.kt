package ffc.entity.place

enum class Education(val names: List<String>) {
    PreKindergarten("เตรียมอนุบาล", "ก่อนอนุบาล"),
    Kindergarten("อนุบาล"),
    ElementarySchool("ประถม"),
    JuniorHighSchool("มัธยมต้น"),
    HighSchool("มัธยม", "มัธยมปลาย"),
    TechnicalCollege("วิทยาลัยเทคนิค"),
    College("วิทยาลัย"),
    University("มหาวิทยาลัย");

    constructor(vararg namesArg: String) : this(namesArg.toList())

    companion object {
        fun byName(name: String): Education {
            val education = Education.values().firstOrNull { it.names.contains(name) }
            return education ?: throw IllegalArgumentException("Not found Education of $name")
        }
    }
}
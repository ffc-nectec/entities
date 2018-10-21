package ffc.entity.place

enum class Religion {
    Buddhism,
    Islam,
    Christianity,
    Hinduism,
    Sikhism,
    Judaism,
    Zen,
    Zoroastrianism,
    BahiFaith,
    Etc;

    companion object {
        fun byName(name: String): Religion {
            return when (name) {
                "พุทธ" -> Religion.Buddhism
                "อิสลาม" -> Religion.Islam
                "คริสต์" -> Religion.Christianity
                "ฮินดู" -> Religion.Hinduism
                "ซิกซ์" -> Religion.Sikhism
                "ยิว" -> Religion.Judaism
                "เซน" -> Religion.Zen
                "โซโรอัสเตอร์" -> Religion.Zoroastrianism
                "บาไฮ" -> Religion.BahiFaith
                "อื่นๆ" -> Religion.Etc
                else -> throw IllegalArgumentException("Not found religion for $name")
            }
        }
    }
}

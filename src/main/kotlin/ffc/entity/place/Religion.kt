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
    Etc
}

fun Religion.valueOfTh(th: String): Religion {
    return when (th) {
        "พุทธ" -> Religion.Buddhism
        "อิสลาม" -> Religion.Islam
        "คริสต์" -> Religion.Christianity
        "ฮินดู" -> Religion.Hinduism
        "ซิกซ์" -> Religion.Sikhism
        "ยิว" -> Religion.Judaism
        "เซน" -> Religion.Zen
        "โซโรอัสเตอร์" -> Religion.Zoroastrianism
        "บาไฮ" -> Religion.BahiFaith
        else -> Religion.Etc
    }
}

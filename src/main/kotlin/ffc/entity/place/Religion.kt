package ffc.entity.place

enum class Religion(val th: String) {
    Buddhism("พุทธ"),
    Islam("อิสลาม"),
    Christianity("คริสต์"),
    Hinduism("ฮินดู"),
    Sikhism("ซิกซ์"),
    Judaism("ยิว"),
    Zen("เซน"),
    Zoroastrianism("โซโรอัสเตอร์"),
    BahiFaith("บาไฮ"),
    Etc("อื่นๆ");

    companion object {
        fun byName(name: String): Religion {
            val religion = Religion.values().firstOrNull { name == it.th }
            return religion ?: throw IllegalArgumentException("Not found religion for $name")
        }
    }
}

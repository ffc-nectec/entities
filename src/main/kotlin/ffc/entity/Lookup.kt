package ffc.entity

abstract class Lookup(
    open val id: String,
    open val name: String
) {
    val type = javaClass.simpleName
    val translation: MutableMap<Lang, String> = hashMapOf()
    var link: Link? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Lookup) return false
        if (id != other.id) return false
        return true
    }

    override fun hashCode() = id.hashCode()
}

enum class Lang {
    en, th
}

package ffc.entity

data class Template(
    val value: String,
    val field: String,
    val orgId: String? = null
) {

    init {
        require(field.matches(Regex("^.*\\..*"))) { "field's pattern must be \"ClassName.fieldName\"" }
    }

    val translation: MutableMap<Lang, String>? = null
}

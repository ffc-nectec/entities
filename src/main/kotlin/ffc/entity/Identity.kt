package ffc.entity

interface Identity {
    val id: String
    val type: String
    fun isValid(): Boolean
}

class ThaiCitizenId(override val id: String) : Identity {
    override val type: String = "thailand-citizen-id"

    override fun isValid(): Boolean = id.length == 13
}

class ThaiHouseholdId(override val id: String) : Identity {
    override val type: String = "thailand-household-id"

    override fun isValid(): Boolean = id.length == 11
}

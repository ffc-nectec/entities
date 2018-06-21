package ffc.entity

import com.google.gson.*
import java.lang.reflect.Type

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


class IdentityDeserializer : JsonDeserializer<Identity>, JsonSerializer<Identity> {
    override fun serialize(src: Identity?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return context!!.serialize(src)

    }

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Identity {
        val jsonObj = json.asJsonObject
        return when (jsonObj.get("type").asString) {
            "thailand-citizen-id" -> ThaiCitizenId(jsonObj.get("id").asString)
            "thailand-household-id" -> ThaiHouseholdId(jsonObj.get("id").asString)
            else -> throw IllegalArgumentException("Not support Identity type")
        }
    }
}

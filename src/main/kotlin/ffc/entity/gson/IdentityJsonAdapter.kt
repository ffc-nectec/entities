package ffc.entity.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import ffc.entity.Identity
import ffc.entity.THAI_CITIZEN_ID
import ffc.entity.THAI_HOUSEHOLD_ID
import ffc.entity.ThaiCitizenId
import ffc.entity.ThaiHouseholdId
import java.lang.reflect.Type

class IdentityJsonAdapter : JsonDeserializer<Identity>,
        JsonSerializer<Identity> {
    override fun serialize(src: Identity, typeOfSrc: Type?, context: JsonSerializationContext): JsonElement {
        return context.serialize(src)
    }

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Identity {
        val jsonObj = json.asJsonObject
        return when (jsonObj.get("type").asString) {
            THAI_CITIZEN_ID -> ThaiCitizenId(jsonObj.get("id").asString)
            THAI_HOUSEHOLD_ID -> ThaiHouseholdId(jsonObj.get("id").asString)
            else -> throw IllegalArgumentException("Not support Identity type")
        }
    }
}

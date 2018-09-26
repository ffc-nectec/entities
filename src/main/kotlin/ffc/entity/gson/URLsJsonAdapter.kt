package ffc.entity.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import ffc.entity.util.URLs
import java.lang.reflect.Type

class URLsJsonAdapter : JsonDeserializer<URLs> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): URLs {
        val url = URLs()
        json.asJsonArray.forEach { url.add(it.asString) }
        return url
    }
}

package ffc.entity.gson

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import ffc.entity.User
import java.lang.reflect.Type

class UserJsonAdapter() : JsonSerializer<User> {

    val gson = GsonBuilder()
            .adapterForExtLibrary()
            .create()

    override fun serialize(user: User, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return gson.toJsonTree(user).apply {
            if (!user.isTempId) asJsonObject.remove("password")
        }
    }
}

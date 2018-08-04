package ffc.entity.gson

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes

internal class ExcludeAnnotationStrategy : ExclusionStrategy {

    override fun shouldSkipField(f: FieldAttributes): Boolean {
        return f.getAnnotation(JsonExclude::class.java) != null
    }

    override fun shouldSkipClass(clazz: Class<*>): Boolean {
        return false
    }
}

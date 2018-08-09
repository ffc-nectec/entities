package ffc.entity

import ffc.entity.gson.JsonExclude
import ffc.entity.util.generateTempId
import org.joda.time.DateTime
import java.util.concurrent.ConcurrentHashMap

@Suppress("UNCHECKED_CAST")
open class Entity(id: String = generateTempId()) : Cloneable {
    var id: String = id
        internal set
    val isTempId: Boolean get() = id.length == 32
    open val type = javaClass.simpleName
    var timestamp: DateTime = DateTime.now()
        internal set
    @JsonExclude
    val bundle: ConcurrentHashMap<String, Any> = ConcurrentHashMap()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Entity
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int = id.hashCode()

    internal fun _clone() = clone()
}

fun <T : Entity> T.update(
    timestamp: DateTime = DateTime.now(),
    block: T.() -> Unit
): T {
    with(this as T) {
        this.timestamp = timestamp
        apply(block)
    }
    return this
}

fun <T : Entity> T.copy(newId: String = this.id): T {
    @Suppress("UNCHECKED_CAST")
    val clone = _clone() as T
    clone.id = newId
    return clone
}

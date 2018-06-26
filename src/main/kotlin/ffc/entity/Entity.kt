package ffc.entity

import ffc.entity.gson.Exclude
import ffc.entity.util.generateTempId
import org.joda.time.DateTime
import java.util.concurrent.ConcurrentHashMap

@Suppress("UNCHECKED_CAST")
open class Entity(id: String = generateTempId()) : Cloneable {
    var id: String = id
        private set
    val isTempId: Boolean get() = id.length == 32
    open val type = javaClass.simpleName
    var timestamp: DateTime = DateTime.now()
        protected set
    @Exclude
    val bundle: ConcurrentHashMap<String, Any> = ConcurrentHashMap()

    fun <T : Entity> update(
        timestamp: DateTime = DateTime.now(),
        block: T.() -> Unit
    ): T {
        with(this as T) {
            apply(block)
            this.timestamp = timestamp
        }
        return this
    }

    fun <T : Entity> copy(id: String = this.id): T {
        val cloneObj = this.clone() as T
        cloneObj.id = id
        return cloneObj
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Entity
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int = id.hashCode()
}

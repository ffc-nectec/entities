package ffc.entity

import ffc.entity.util.generateTempId
import org.joda.time.LocalDateTime

open class Entity(id: String = generateTempId()) : Cloneable {
    var id: String private set
    val type = javaClass.simpleName

    init { this.id = id }

    fun <T : Entity> update(updateTimestamp: Boolean = true, block: T.() -> Unit): T {
        this as T
        this.apply(block)
        if (updateTimestamp) timestamp = LocalDateTime.now()
        return this
    }

    var timestamp: LocalDateTime = LocalDateTime.now()
        protected set

    val isTempId: Boolean get() = id.length == 32

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Entity
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    fun <T : Entity> copy(id: String = this.id): T {
        val cloneObj = this.clone() as T
        cloneObj.id = id
        return cloneObj
    }
}

package ffc.entity

import com.google.gson.annotations.SerializedName
import org.joda.time.LocalDateTime
import java.util.UUID

open class Entity(
    @SerializedName("_id") val id: String = "${UUID.randomUUID()}"
) {

    fun <T : Entity> update(updateTimestamp: Boolean = true,block: T.() -> Unit): T  {
        this as T
        this.apply(block)
        if (updateTimestamp) timestamp = LocalDateTime.now()
        return this
    }

    var timestamp: LocalDateTime = LocalDateTime.now()
        protected set

    @SerializedName("_sync")
    var isSynced: Boolean? = null

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
}


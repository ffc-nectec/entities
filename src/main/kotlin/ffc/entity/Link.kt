package ffc.entity

import org.joda.time.DateTime
import java.util.concurrent.ConcurrentHashMap

data class Link(val system: System, var keys: MutableMap<String, Any>) {

    var isSynced: Boolean = true
        set(value) {
            field = value
            if (value == true)
                lastSync = DateTime.now()
        }

    var lastSync: DateTime? = DateTime.now()
        private set

    constructor(
        system: System,
        vararg keys: Pair<String, Any>
    ) : this(system, keys.toMap(ConcurrentHashMap()).toMutableMap())
}

enum class System {
    JHICS, HOSxP
}

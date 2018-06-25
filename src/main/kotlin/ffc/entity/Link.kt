package ffc.entity

import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime

data class Link(val system: System, val keys: Map<String, String>) {

   var isSynced: Boolean = true
        set(value)  {
            field = value
            if (value == true)
                lastSync = DateTime.now()
        }
    var lastSync: DateTime? = DateTime.now()
        private set

    constructor(system: System, vararg keys: Pair<String, String>) : this(system, keys.toMap())
}

enum class System {
    JHICS, HOSxP
}

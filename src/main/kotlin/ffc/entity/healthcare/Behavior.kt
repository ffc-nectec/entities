package ffc.entity.healthcare

import org.joda.time.DateTime

data class Behavior(
    var ciga: Frequency? = null,
    var wisky: Frequency? = null,
    var exercise: Frequency? = null,
    var bigaccidentever: Frequency? = null,
    var tonic: Frequency? = null,
    var habitfoming: Frequency? = null,
    var drugbyyourseft: Frequency? = null,
    var sugar: Frequency? = null,
    var salt: Frequency? = null
) {
    var timestamp: DateTime = DateTime.now()
        internal set
}

fun Behavior.update(
    timestamp: DateTime = DateTime.now(),
    block: Behavior.() -> Unit
): Behavior {
    with(this) {
        this.timestamp = timestamp
        apply(block)
    }
    return this
}

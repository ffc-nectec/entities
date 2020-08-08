package ffc.entity.healthcare

import org.joda.time.DateTime

data class Behavior(
    var smoke: Frequency? = null,
    var alcohol: Frequency? = null,
    var exercise: Frequency? = null,
    var bigaccidentever: Boolean? = null,
    var tonic: Boolean? = null,
    var habitfoming: Boolean? = null,
    var drugbyyourseft: Boolean? = null,
    var sugar: Boolean? = null,
    var salt: Boolean? = null
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

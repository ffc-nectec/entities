package ffc.entity.healthcare

import org.amshove.kluent.`should be`
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ThaiBMITest(
    val bmi: ThaiBMI,
    val isUnderWeight: Boolean,
    val isNormal: Boolean,
    val isOverWeight: Boolean,
    val isObese: Boolean
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
                arrayOf(ThaiBMI(1.60, 60.0), false, true, false, false),
                arrayOf(ThaiBMI(1.60, 40.0), true, false, false, false)
        )
    }

    @Test
    fun isUnderWeight() {
        bmi.isUnderWeight `should be` isUnderWeight
    }

    @Test
    fun isNormal() {
        bmi.isNormal `should be` isNormal
    }

    @Test
    fun isOverweight() {
        bmi.isOverweight `should be` isOverWeight
    }

    @Test
    fun isObese() {
        bmi.isObese `should be` isObese
    }
}

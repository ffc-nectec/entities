package ffc.entity.healthcare

import org.amshove.kluent.`should be`
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ThaiBloodPressureTest(
    val bp: BloodPressureAnalyzer,
    val isLow: Boolean,
    val isNormal: Boolean,
    val isPreHigh: Boolean,
    val isHigh: Boolean
) {

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
                arrayOf(ThaiBloodPressure(90.0, 60.0), false, true, false, false),
                arrayOf(ThaiBloodPressure(60.0, 50.0), true, false, false, false),
                arrayOf(ThaiBloodPressure(119.0, 79.0), false, true, false, false),
                arrayOf(ThaiBloodPressure(120.0, 80.0), false, false, true, false),
                arrayOf(ThaiBloodPressure(125.0, 85.0), false, false, true, false),
                arrayOf(ThaiBloodPressure(139.0, 89.0), false, false, true, false),
                arrayOf(ThaiBloodPressure(140.0, 99.0), false, false, false, true),
                arrayOf(ThaiBloodPressure(180.0, 110.0), false, false, false, true)
                )
    }

    @Test
    fun isLow() {
        bp.isLow `should be` isLow
    }

    @Test
    fun isNormal() {
        bp.isNormal `should be` isNormal
    }

    @Test
    fun isPreHigh() {
        bp.isPreHigh `should be` isPreHigh
    }

    @Test
    fun isHigh() {
        bp.isHigh `should be` isHigh
    }
}

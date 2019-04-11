package ffc.entity.healthcare.analyze

import org.joda.time.LocalDate

abstract class HealthIssue(
    val issue: Issue,
    val date: LocalDate = LocalDate.now()
) {
    abstract val haveIssue: Boolean
    val type = javaClass.simpleName

    enum class Issue {
        DM, HT, CVD, DEMENTIA, DEPRESSIVE, OA_KNEE,
        FARSIGHTED, NEARSIGHTED, CATARACT, GLAUCOMA, AMD,
        FALL_RISK, ACTIVITIES
    }
}

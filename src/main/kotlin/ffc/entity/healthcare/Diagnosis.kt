package ffc.entity.healthcare

class Diagnosis(val disease: Disease, val dxType: Type) {

    enum class Type {
        PRINCIPLE_DX,
        CO_MORBIDITY,
        COMPLICATION,
        OTHER,
        EXTERNAL_CAUSE
    }
}

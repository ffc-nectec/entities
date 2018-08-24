package ffc.entity.healthcare

import ffc.entity.Lookup

class Disease(
    id: String,
    name: String,
    val icd10: String? = null,
    val isEpimedic: Boolean = false,
    val isChronic: Boolean = false,
    val isNCD: Boolean = false
) : Lookup(id, name)

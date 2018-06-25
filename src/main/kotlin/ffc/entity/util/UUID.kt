package ffc.entity.util

import java.util.UUID

fun generateTempId(): String = UUID.randomUUID().toString().replace("-", "")

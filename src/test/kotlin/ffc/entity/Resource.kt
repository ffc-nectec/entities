package ffc.entity

fun Any.resourceFile(filename: String) =
        this::class.java.classLoader.getResource(filename).readText()

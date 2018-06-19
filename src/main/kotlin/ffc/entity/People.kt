package ffc.entity

data class People(val id: String, val name: String, val chronics: MutableList<Chronic>? = null)

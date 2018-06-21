package ffc.model

data class Messaging(val _id: String, val type: Type, val url: String) {
    enum class Type {
        House
    }
}

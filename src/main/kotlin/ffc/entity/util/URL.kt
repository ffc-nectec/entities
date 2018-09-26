package ffc.entity.util

import java.net.URI
import java.util.ArrayList

class URLs(vararg urls: String) : ArrayList<String>() {

    init {
        addAll(urls)
    }

    override fun add(url: String): Boolean {
        checkValidUrl(url)
        return super.add(url)
    }

    override fun addAll(elements: Collection<String>): Boolean {
        elements.forEach { checkValidUrl(it) }
        return super.addAll(elements)
    }
}

internal fun checkValidUrl(url: String) = URI(url).toURL()

package ffc.entity.healthcare

import ffc.entity.Link
import ffc.entity.Lookup

class CommunityServiceType(
    id: String,
    name: String
) : Lookup(id, name) {
    var link: Link? = null
}

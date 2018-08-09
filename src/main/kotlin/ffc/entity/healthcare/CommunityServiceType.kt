package ffc.entity.healthcare

import ffc.entity.Entity
import ffc.entity.Link
import ffc.entity.util.generateTempId

class CommunityServiceType(id: String = generateTempId(), name: String) : Entity(id) {
    var link: Link? = null
}

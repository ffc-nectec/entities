/*
 * Copyright 2019 NECTEC
 *   National Electronics and Computer Technology Center, Thailand
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ffc.entity.healthcare

import ffc.entity.Entity
import ffc.entity.Lookup

open class CommunityService(val serviceType: ServiceType) : Entity() {

    class ServiceType(id: String, name: String) : Lookup(id, name)
}

class HomeVisit(
    serviceType: CommunityService.ServiceType,
    var detail: String? = null,
    var result: String? = null,
    var plan: String? = null
) : CommunityService(serviceType)

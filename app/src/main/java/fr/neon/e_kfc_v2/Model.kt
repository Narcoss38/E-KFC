package fr.neon.e_kfc_v2

import kotlin.properties.Delegates

class Model {
    var temperature by Delegates.notNull<Long>()

    constructor(temperature: Long) {
        this.temperature = temperature
    }

    constructor()
}

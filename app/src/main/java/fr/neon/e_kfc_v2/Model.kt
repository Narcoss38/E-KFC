package fr.neon.e_kfc_v2

import kotlin.properties.Delegates

class Model {
    var temperature by Delegates.notNull<Double>()

    constructor(temperature: Double) {
        this.temperature = temperature
    }

    constructor()
}

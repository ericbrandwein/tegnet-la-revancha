package objetivos

import paises.Continente

class ObjetivoDeOcupar5 : ObjetivoDeOcupar() {
    override val descripcion =
            "Ocupar 4 países de América del Norte, 4 de Europa, 4 de Asia, " +
                    "3 de América del Sur, 3 de América Central, " +
                    "3 de Africa y 3 de Oceanía."

    init {
        paisesDeContinentesAOcupar = mapOf(
                Continente.AMERICA_DEL_NORTE to 4,
                Continente.EUROPA to 4,
                Continente.ASIA to 4,
                Continente.AMERICA_DEL_SUR to 3,
                Continente.AMERICA_CENTRAL to 3,
                Continente.AFRICA to 3,
                Continente.OCEANIA to 3
        )
    }
}
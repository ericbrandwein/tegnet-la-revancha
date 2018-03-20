package objetivos

import paises.Continente

class ObjetivoDeOcupar7 : ObjetivoDeOcupar() {
    override val descripcion =
            "Ocupar América Central, 6 países de América del Sur, " +
                    "6 de Europa y 6 de Asia."

    init {
        paisesDeContinentesAOcupar = mapOf(
                Continente.AMERICA_CENTRAL to TODOS_LOS_PAISES,
                Continente.AMERICA_DEL_SUR to 6,
                Continente.EUROPA to 6,
                Continente.ASIA to 6
        )
    }
}
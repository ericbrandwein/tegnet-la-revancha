package objetivos

import paises.Continente

class ObjetivoDeOcupar10 : ObjetivoDeOcupar() {
    override val descripcion =
            "Ocupar Europa, 4 países de Asia, 4 países de América del Sur."

    init {
        paisesDeContinentesAOcupar = mapOf(
                Continente.EUROPA to TODOS_LOS_PAISES,
                Continente.ASIA to 4,
                Continente.AMERICA_DEL_SUR to 4
        )
    }
}
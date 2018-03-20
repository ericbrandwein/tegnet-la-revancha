package objetivos

import paises.Continente

class ObjetivoDeOcupar8 : ObjetivoDeOcupar() {
    override val descripcion =
            "Ocupar América del Sur, Africa y 8 países de Asia."

    init {
        paisesDeContinentesAOcupar = mapOf(
                Continente.AMERICA_DEL_SUR to TODOS_LOS_PAISES,
                Continente.AFRICA to TODOS_LOS_PAISES,
                Continente.ASIA to 8
        )
    }
}
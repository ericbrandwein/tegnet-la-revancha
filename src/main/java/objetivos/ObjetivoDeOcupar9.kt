package objetivos

import paises.Continente

class ObjetivoDeOcupar9 : ObjetivoDeOcupar() {
    override val descripcion =
            "Ocupar Oceanía, Africa, 4 países de América Central y 4 de Asia."

    init {
        paisesDeContinentesAOcupar = mapOf(
                Continente.OCEANIA to TODOS_LOS_PAISES,
                Continente.AFRICA to TODOS_LOS_PAISES,
                Continente.AMERICA_CENTRAL to 4,
                Continente.ASIA to 4
        )
    }
}
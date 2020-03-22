package objetivos

import paises.Continente

class ObjetivoDeOcupar3 : ObjetivoDeOcupar() {
    override val descripcion = "Ocupar Asia y América Central."

    init {
        paisesDeContinentesAOcupar = mapOf(
                Continente.ASIA to TODOS_LOS_PAISES,
                Continente.AMERICA_CENTRAL to TODOS_LOS_PAISES
        )
    }
}
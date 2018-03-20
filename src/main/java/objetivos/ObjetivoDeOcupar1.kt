package objetivos

import paises.Continente

class ObjetivoDeOcupar1 : ObjetivoDeOcupar() {
    override val descripcion = "Ocupar Europa y América del Sur"

    init {
        paisesDeContinentesAOcupar = mapOf(
                Continente.EUROPA to TODOS_LOS_PAISES,
                Continente.AMERICA_DEL_SUR to TODOS_LOS_PAISES
        )
    }
}
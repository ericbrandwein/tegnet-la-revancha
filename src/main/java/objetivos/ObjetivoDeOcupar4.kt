package objetivos

import paises.Continente

class ObjetivoDeOcupar4 : ObjetivoDeOcupar() {
    override val descripcion =
            "Ocupar América del Norte, 8 países de Asia y 4 de Europa"

    init {
        paisesDeContinentesAOcupar = mapOf(
                Continente.AMERICA_DEL_NORTE to TODOS_LOS_PAISES,
                Continente.ASIA to 8,
                Continente.EUROPA to 4
        )
    }
}
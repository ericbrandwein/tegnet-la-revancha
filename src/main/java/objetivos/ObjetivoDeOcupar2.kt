package objetivos

import paises.Continente

class ObjetivoDeOcupar2 : ObjetivoDeOcupar() {
    override val descripcion =
            "Ocupar América del Norte, Oceanía y 5 países de Africa."

    init {
        paisesDeContinentesAOcupar = mapOf(
                Continente.AMERICA_DEL_NORTE to TODOS_LOS_PAISES,
                Continente.OCEANIA to TODOS_LOS_PAISES,
                Continente.AFRICA to 5
        )
    }
}
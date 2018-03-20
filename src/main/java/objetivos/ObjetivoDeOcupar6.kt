package objetivos

import paises.Continente

class ObjetivoDeOcupar6 : ObjetivoDeOcupar() {
    override val descripcion = "Ocupar Oceanía, 6 países de Asia, " +
            "6 de Africa y 6 de América del Norte."

    init {
        paisesDeContinentesAOcupar = mapOf(
                Continente.OCEANIA to TODOS_LOS_PAISES,
                Continente.ASIA to 6,
                Continente.AFRICA to 6,
                Continente.AMERICA_DEL_NORTE to 6
        )
    }
}
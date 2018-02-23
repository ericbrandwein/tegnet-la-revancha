package objetivos

import Jugador
import paises.Continente
import paises.PaisEnJuego

class ObjetivoDeOcupar2 : ObjetivoDeOcupar {
    override val descripcion =
            "Ocupar América del Norte, Oceanía y 5 países de Africa."

    override fun cumplido(paises: List<PaisEnJuego>, conquistado: PaisEnJuego,
            jugadores: List<Jugador>, jugadorActual: Int): Boolean {
        val americaDelNorteOcupado = continenteConquistado(
                paises, Continente.AMERICA_DEL_NORTE, jugadorActual,
                conquistado)
        val oceaniaConquistado = continenteConquistado(
                paises, Continente.OCEANIA, jugadorActual, conquistado)
        val cantOcupadosDeAfrica = cantPaisesDeContinenteConDueno(
                paises, Continente.AFRICA, jugadorActual, conquistado)
        return americaDelNorteOcupado && oceaniaConquistado &&
                cantOcupadosDeAfrica >= 5
    }
}
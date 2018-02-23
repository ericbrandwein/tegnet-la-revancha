package objetivos

import Jugador
import paises.Continente
import paises.PaisEnJuego

class ObjetivoDeOcupar1 : ObjetivoDeOcupar {
    override val descripcion = "Ocupar Europa y América del Sur"

    override fun cumplido(paises: List<PaisEnJuego>, conquistado: PaisEnJuego,
                          jugadores: List<Jugador>,
                          jugadorActual: Int): Boolean {
        val europaConquistada =
                continenteConquistado(paises, jugadorActual, Continente.EUROPA,
                        conquistado)
        val americaDelSurConquistada =
                continenteConquistado(paises, jugadorActual,
                        Continente.AMERICA_DEL_SUR, conquistado)
        return europaConquistada && americaDelSurConquistada
    }
}
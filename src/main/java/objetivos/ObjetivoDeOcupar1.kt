package objetivos

import juego.Jugador
import paises.Continente
import paises.PaisEnJuego
import paises.continenteOcupado

class ObjetivoDeOcupar1 : ObjetivoDeOcupar {
    override val descripcion = "Ocupar Europa y América del Sur"

    override fun cumplido(paises: Iterable<PaisEnJuego>,
            jugadores: List<Jugador>, jugadorActual: Int,
            jugadorAtacado: Int): Boolean {
        val europaConquistada = continenteOcupado(
                paises, Continente.EUROPA, jugadorActual)
        val americaDelSurConquistada = continenteOcupado(
                paises, Continente.AMERICA_DEL_SUR, jugadorActual)
        return europaConquistada && americaDelSurConquistada
    }
}
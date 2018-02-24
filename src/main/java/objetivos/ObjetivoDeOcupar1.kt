package objetivos

import juego.Jugador
import paises.Continente
import paises.PaisEnJuego

class ObjetivoDeOcupar1 : ObjetivoDeOcupar {
    override val descripcion = "Ocupar Europa y América del Sur"

    override fun cumplido(paises: List<PaisEnJuego>, conquistado: PaisEnJuego,
            jugadores: List<Jugador>, jugadorActual: Int): Boolean {
        val europaConquistada = paises.continenteConquistado(
                paises, Continente.EUROPA, jugadorActual, conquistado)
        val americaDelSurConquistada = paises.continenteConquistado(
                paises, Continente.AMERICA_DEL_SUR, jugadorActual, conquistado)
        return europaConquistada && americaDelSurConquistada
    }
}
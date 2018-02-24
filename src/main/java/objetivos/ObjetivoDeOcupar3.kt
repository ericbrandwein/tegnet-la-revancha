package objetivos

import juego.Jugador
import paises.Continente
import paises.PaisEnJuego

class ObjetivoDeOcupar3 : ObjetivoDeOcupar {
    override val descripcion = "Ocupar Asia y América Central."

    override fun cumplido(paises: List<PaisEnJuego>, conquistado: PaisEnJuego,
            jugadores: List<Jugador>, jugadorActual: Int): Boolean {
        val asiaConquistado = paises.continenteConquistado(
                paises, Continente.ASIA, jugadorActual, conquistado)
        val americaCentralConquistado = paises.continenteConquistado(
                paises, Continente.AMERICA_CENTRAL, jugadorActual, conquistado)
        return asiaConquistado && americaCentralConquistado
    }
}
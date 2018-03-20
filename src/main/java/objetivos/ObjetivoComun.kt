package objetivos

import juego.Jugador
import paises.PaisEnJuego
import paises.cantPaisesConDueno

class ObjetivoComun : ObjetivoDeOcupar {
    override val descripcion = "Ocupar 45 países."

    override fun cumplido(paises: Iterable<PaisEnJuego>,
            jugadores: List<Jugador>, jugadorActual: Int,
            jugadorAtacado: Int): Boolean =
            cantPaisesConDueno(paises, jugadorActual) >= 45
}
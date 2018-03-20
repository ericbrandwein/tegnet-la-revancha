package objetivos

import juego.Jugador
import paises.PaisEnJuego
import paises.cantPaisesConDueno

class ObjetivoDeOcupar12 : ObjetivoDeOcupar() {
    override val descripcion = "Ocupar 35 países en cualquier lugar del mapa."

    override fun cumplido(paises: Iterable<PaisEnJuego>,
            jugadores: List<Jugador>, jugadorActual: Int,
            jugadorAtacado: Int) =
            cantPaisesConDueno(paises, jugadorActual) >= 35
}
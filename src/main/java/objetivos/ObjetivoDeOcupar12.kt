package objetivos

import juego.Jugador
import paises.PaisEnJuego

class ObjetivoDeOcupar12 : ObjetivoDeOcupar {
    override val descripcion = "Ocupar 35 países en cualquier lugar del mapa."

    override fun cumplido(paises: List<PaisEnJuego>, conquistado: PaisEnJuego,
            jugadores: List<Jugador>, jugadorActual: Int): Boolean =
            paises.cantPaisesConDueno(paises, jugadorActual) + 1 >= 35

}
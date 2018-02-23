package objetivos

import Jugador
import paises.PaisEnJuego

class ObjetivoComun : Objetivo {
    override val descripcion = "Ocupar 45 países."

    override fun cumplido(paises: List<PaisEnJuego>, conquistado: PaisEnJuego,
                          jugadores: List<Jugador>,
                          jugadorActual: Int): Boolean =
            cantPaisesConDueno(paises, jugadorActual) + 1 >= 45

    override fun puedeCumplirse(paises: List<PaisEnJuego>,
                                jugadores: List<Jugador>,
                                jugadorActual: Int): Boolean = true
}
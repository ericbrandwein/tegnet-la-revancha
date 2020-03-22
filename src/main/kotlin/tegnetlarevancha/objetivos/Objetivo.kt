package objetivos

import juego.Jugador
import paises.PaisEnJuego

interface Objetivo {
    val descripcion: String

    /**
     * Determina si se cumplio el objetivo del [jugadorActual]
     * despues de haber conquistado un pais del [jugadorAtacado].
     *
     * @param jugadores lista de jugadores en orden de la vuelta, tal que
     * `jugadores[i + 1]` esta a la izquierda de `jugadores[i]`
     */
    fun cumplido(paises: Iterable<PaisEnJuego>, jugadores: List<Jugador>,
            jugadorActual: Int, jugadorAtacado: Int): Boolean

    /**
     * Determina si se puede cumplir el objetivo.
     */
    fun puedeCumplirse(paises: Iterable<PaisEnJuego>, jugadores: List<Jugador>,
            jugadorActual: Int): Boolean
}
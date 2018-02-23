package objetivos

import juego.Jugador
import paises.PaisEnJuego

interface Objetivo {
    val descripcion: String

    /**
     * Determina si se habra cumplido el objetivo del jugador actual
     * despues de haber conquistado el pais.
     *
     * @param jugadores lista de jugadores en orden de la vuelta, tal que
     * `jugadores[i + 1]` esta a la izquierda de `jugadores[i]`
     */
    fun cumplido(paises: List<PaisEnJuego>, conquistado: PaisEnJuego,
                 jugadores: List<Jugador>, jugadorActual: Int): Boolean

    /**
     * Determina si se puede cumplir el objetivo.
     */
    fun puedeCumplirse(paises: List<PaisEnJuego>, jugadores: List<Jugador>,
                       jugadorActual: Int): Boolean
}
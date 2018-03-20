package objetivos

import juego.Jugador
import paises.PaisEnJuego

interface ObjetivoDeOcupar : Objetivo {
    override fun puedeCumplirse(paises: Iterable<PaisEnJuego>,
            jugadores: List<Jugador>, jugadorActual: Int) = true
}
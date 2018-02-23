package objetivos

import Jugador
import paises.PaisEnJuego

interface ObjetivoDeOcupar : Objetivo {
    override fun puedeCumplirse(paises: List<PaisEnJuego>,
                                jugadores: List<Jugador>,
                                jugadorActual: Int): Boolean = true

}
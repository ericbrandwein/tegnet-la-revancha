package objetivos

import Jugador
import paises.PaisEnJuego

abstract class ObjetivoDeDestruir : Objetivo {
    override fun cumplido(paises: List<PaisEnJuego>, conquistado: PaisEnJuego,
                          jugadores: List<Jugador>,
                          jugadorActual: Int): Boolean {
        val indiceEnemigo = jugadorADestruir(jugadores, jugadorActual)
        return esSuUltimoPais(paises, conquistado, indiceEnemigo)
    }

    override fun puedeCumplirse(paises: List<PaisEnJuego>,
                                jugadores: List<Jugador>,
                                jugadorActual: Int): Boolean {

        val indiceEnemigo = jugadorADestruir(jugadores, jugadorActual)
        return !jugadores[indiceEnemigo].perdio
    }

    private fun esSuUltimoPais(paises: List<PaisEnJuego>, pais: PaisEnJuego,
                               jugador: Int): Boolean {
        return pais.dueno == jugador &&
                cantPaisesConDueno(paises, jugador) == 1
    }

    /**
     * Determina el indice en el arreglo de jugadores al que se debe destruir
     */
    protected abstract fun jugadorADestruir(jugadores: List<Jugador>,
                                            jugadorActual: Int): Int
}
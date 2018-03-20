package objetivos

import juego.Jugador
import paises.PaisEnJuego
import paises.cantPaisesConDueno

abstract class ObjetivoDeDestruir : Objetivo {
    override fun cumplido(paises: Iterable<PaisEnJuego>,
            jugadores: List<Jugador>, jugadorActual: Int,
            jugadorAtacado: Int): Boolean {
        val indiceEnemigo = jugadorADestruir(jugadores, jugadorActual)
        return jugadorAtacado == indiceEnemigo &&
                seQuedoSinPaises(paises, jugadorAtacado)
    }

    override fun puedeCumplirse(paises: Iterable<PaisEnJuego>,
            jugadores: List<Jugador>,
            jugadorActual: Int): Boolean {
        val indiceEnemigo = jugadorADestruir(jugadores, jugadorActual)
        return !jugadores[indiceEnemigo].perdio
    }

    private fun seQuedoSinPaises(paises: Iterable<PaisEnJuego>,
            jugador: Int) = cantPaisesConDueno(paises, jugador) == 0

    /**
     * Determina el indice en el arreglo de jugadores al que se debe destruir
     */
    protected abstract fun jugadorADestruir(jugadores: List<Jugador>,
            jugadorActual: Int): Int
}
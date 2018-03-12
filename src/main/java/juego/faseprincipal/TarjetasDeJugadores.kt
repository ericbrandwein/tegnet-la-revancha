package juego.faseprincipal

import juego.Mazo
import paises.PaisEnJuego

class TarjetasDeJugadores(jugadores: Int, paises: List<PaisEnJuego>) {
    private val tarjetas: MutableList<MutableSet<PaisEnJuego>> =
            ArrayList(jugadores)
    private val mazoDePaises = Mazo(paises)

    init {
        for (i in 1..jugadores) {
            tarjetas[i] = mutableSetOf()
        }
    }

    fun sacarTarjeta(jugador: Int): PaisEnJuego {
        val tarjeta = mazoDePaises.sacarTarjeta()
        tarjetas[jugador].add(tarjeta)
        return tarjeta
    }

    fun tarjetas(jugador: Int) = setOf(tarjetas[jugador])

    fun canjear(jugador: Int) {
        TODO()
    }
}
package juego.faseprincipal.tarjetasdepaises

import juego.Mazo
import paises.PaisEnJuego

private const val EJERCITOS_PRIMER_CANJE = 6

class TarjetasDeJugadores(jugadores: Int, paises: List<PaisEnJuego>) {
    private val tarjetas: MutableList<MutableSet<PaisEnJuego>> =
            ArrayList(jugadores)
    private val mazoDePaises = Mazo(paises)
    private val canjes: MutableList<Int> = ArrayList(jugadores)

    init {
        for (i in 1..jugadores) {
            tarjetas[i] = mutableSetOf()
            canjes[i] = 0
        }
    }

    fun sacarTarjeta(jugador: Int): PaisEnJuego {
        val tarjeta = mazoDePaises.sacarTarjeta()
        tarjetas[jugador].add(tarjeta)
        return tarjeta
    }

    fun tarjetasDe(jugador: Int) = setOf(tarjetas[jugador])

    fun cantEjercitosParaCanje(numeroCanje: Int): Int {
        return if (numeroCanje == 1) EJERCITOS_PRIMER_CANJE
        else numeroCanje * 5
    }

    fun cantCanjes(jugador: Int) = canjes[jugador]

    fun canjear(jugador: Int) {

    }
}
package juego.faseprincipal.tarjetasconsimbolos

import juego.mazo.MazoAgregable
import paises.Continente

private const val EJERCITOS_PRIMER_CANJE = 6

class TarjetasDeJugadores(jugadores: Int) {
    private val tarjetasDePais: MutableList<MutableSet<TarjetaDePais>> =
            ArrayList(jugadores)
    private val tarjetasDeContinente: MutableMap<TarjetaDeContinente, Int> =
            deserializarTarjetasDeContinentes().associate { it to -1 }
                    .toMutableMap()
    private val mazoDePaises = MazoAgregable(deserializarTarjetasDePaises())
    private val canjes: MutableList<Int> = ArrayList(jugadores)

    init {
        for (i in 1..jugadores) {
            tarjetasDePais[i] = mutableSetOf()
            canjes[i] = 0
        }
    }

    fun sacarTarjetaDePais(jugador: Int): TarjetaDePais {
        val tarjeta = mazoDePaises.sacarTarjeta()
        tarjetasDePais[jugador].add(tarjeta)
        return tarjeta
    }

    fun entregarTarjetaDeContinente(jugador: Int, continente: Continente) {
        tarjetasDeContinente.forEach { clave, _ ->
            if (clave.continente == continente) {
                tarjetasDeContinente[clave] = jugador
            }
        }
    }

    fun tarjetasDePaisDe(jugador: Int) = setOf(tarjetasDePais[jugador])

    fun tarjetasDeContinenteDe(jugador: Int) =
            tarjetasDeContinente.filterValues { it == jugador }.keys

    fun devolverTarjetasDePais(vararg tarjetas: TarjetaDePais) {
        mazoDePaises.agregarTarjetas(tarjetas)

        tarjetasDePais.forEach { setDeTarjetas ->
            setDeTarjetas.removeAll(tarjetas)
        }
    }

    fun devolverTarjetasDeContinente(vararg tarjetas: TarjetaDeContinente) {
        tarjetas.forEach {
            tarjetasDeContinente[it] = -1
        }
    }

    // TODO: Pasar la parte de los canjes a otro lugar.

    fun cantEjercitosParaCanje(numeroDeCanje: Int): Int {
        return if (numeroDeCanje == 1) EJERCITOS_PRIMER_CANJE
        else numeroDeCanje * 5
    }

    fun cantCanjes(jugador: Int) = canjes[jugador]

    fun puedeHacerCanje(jugador: Int) {

    }

    fun canjear(jugador: Int, tarjetas: List<TarjetaConSimbolos>) {

    }
}
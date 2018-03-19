package juego.faseprincipal.canjes

import juego.faseprincipal.tarjetasconsimbolos.*
import paises.Continente

private const val EJERCITOS_PRIMER_CANJE = 6

class Canjeador(jugadores: Int,
        private val tarjetasDeJugadores: TarjetasDeJugadores) {
    private val canjes = (1..jugadores).map { 0 }.toMutableList()
    private val tarjetasDeContinenteCanjeadas =
            (1..jugadores).map { mutableSetOf<Continente>() }

    /**
     * Determina cuantos ejercitos se obtienen al hacer el
     * canje numero [numeroDeCanje]. Los numeros comienzan desde el 1.
     */
    fun cantEjercitosParaCanje(numeroDeCanje: Int) =
            if (numeroDeCanje == 1) EJERCITOS_PRIMER_CANJE
            else numeroDeCanje * 5

    /**
     * Determina cuantos canjes hizo el [jugador].
     */
    fun cantCanjes(jugador: Int) = canjes[jugador]

    /**
     * Determina si el [jugador] puede hacer un canje con las tarjetas que posee.
     */
    fun puedeHacerCanje(jugador: Int): Boolean {
        val tarjetasDePais: Set<TarjetaDePais> =
                tarjetasDeJugadores.tarjetasDePaisDe(jugador)
        val tarjetasDeContinente: Set<TarjetaDeContinente> =
                tarjetasDeJugadores.tarjetasDeContinenteDe(jugador)

        return puedeHacerCanjeConTarjetas(tarjetasDePais + tarjetasDeContinente)
    }

    /**
     * Determina si se puede hacer un canje con algunas de estas [tarjetas].
     */
    fun puedeHacerCanjeConTarjetas(tarjetas: Set<TarjetaConSimbolos>): Boolean {
        val cantidadSimbolos: MutableMap<Simbolo, Int> =
                Simbolo.values().associate { it to 0 }.toMutableMap()
        tarjetas.map { tarjeta -> tarjeta.simbolos }.flatMap { it }
                .forEach { simbolo ->
                    cantidadSimbolos[simbolo] = cantidadSimbolos[simbolo]!! + 1
                }

        return tieneTresDiferentes(cantidadSimbolos)
                || tieneTresIguales(cantidadSimbolos)
    }

    private fun tieneTresDiferentes(cantidadSimbolos: Map<Simbolo, Int>) =
            Simbolo.values().count { cantidadSimbolos[it]!! > 0 } >= 3


    private fun tieneTresIguales(cantidadSimbolos: Map<Simbolo, Int>) =
            Simbolo.values().filter { it != Simbolo.COMODIN }.any {
                cantidadSimbolos[it]!! >=
                        3 - cantidadSimbolos[Simbolo.COMODIN]!!
            }

    /**
     * Saca las [tarjetas] del [jugador], y devuelve la cantidad de ejercitos
     * que se obtuvieron.
     */
    fun canjear(jugador: Int, tarjetasDePais: Set<TarjetaDePais>,
            tarjetasDeContinente: Set<TarjetaDeContinente>): Int {
        tarjetasDeJugadores.devolverTarjetasDePais(tarjetasDePais)
        tarjetasDeJugadores.devolverTarjetasDeContinente(tarjetasDeContinente)
        tarjetasDeContinenteCanjeadas[jugador] +=
                tarjetasDeContinente.map { it.continente }
        canjes[jugador]++
        return cantEjercitosParaCanje(canjes[jugador])
    }

    /**
     * Determina si el [jugador] canjeo la tarjeta del [continente].
     */
    fun canjeoTarjetaDeContinente(jugador: Int, continente: Continente) =
            tarjetasDeContinenteCanjeadas[jugador].contains(continente)
}
package juego.faseprincipal.canjes

import juego.faseprincipal.tarjetasconsimbolos.*

private const val EJERCITOS_PRIMER_CANJE = 6

class Canjeador(jugadores: Int,
        private val tarjetasDeJugadores: TarjetasDeJugadores) {
    private val canjes = (1..jugadores).map { 0 }.toMutableList()

    fun cantEjercitosParaCanje(numeroDeCanje: Int): Int {
        return if (numeroDeCanje == 1) EJERCITOS_PRIMER_CANJE
        else numeroDeCanje * 5
    }

    fun cantCanjes(jugador: Int) = canjes[jugador]

    fun puedeHacerCanje(jugador: Int): Boolean {
        val tarjetasDePais: Set<TarjetaDePais> =
                tarjetasDeJugadores.tarjetasDePaisDe(jugador)
        val tarjetasDeContinente: Set<TarjetaDeContinente> =
                tarjetasDeJugadores.tarjetasDeContinenteDe(jugador)

        return puedeHacerCanjeConTarjetas(tarjetasDePais + tarjetasDeContinente)
    }

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

    fun canjear(jugador: Int, tarjetas: List<TarjetaConSimbolos>) {
        canjes[jugador]++
    }
}
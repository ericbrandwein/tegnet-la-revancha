package juego.faseprincipal.tarjetasconsimbolos

import juego.mazo.MazoAgregable
import paises.Continente

class TarjetasDeJugadores(jugadores: Int) {
    private val tarjetasDePais =
            (1..jugadores).map { mutableSetOf<TarjetaDePais>() }
    private val tarjetasDeContinente: MutableMap<TarjetaDeContinente, Int> =
            deserializarTarjetasDeContinentes().associate { it to -1 }
                    .toMutableMap()
    private val mazoDePaises = MazoAgregable(deserializarTarjetasDePaises())


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

    fun tarjetasDePaisDe(jugador: Int) = tarjetasDePais[jugador].toSet()

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
}
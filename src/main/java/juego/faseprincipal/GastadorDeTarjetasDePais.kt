package juego.faseprincipal

import juego.faseprincipal.tarjetasconsimbolos.TarjetaDePais
import juego.faseprincipal.tarjetasconsimbolos.TarjetasDeJugadores

/**
 * Se encarga de decidir a que paises se le pueden agregar ejercitos "gastando"
 * las tarjetas que tiene cada jugador.
 */
class GastadorDeTarjetasDePais(jugadores: Int,
        private val tarjetasDeJugadores: TarjetasDeJugadores) {
    private val tarjetasGastadas: List<MutableList<TarjetaDePais>> =
            (1..jugadores).map { mutableListOf<TarjetaDePais>() }

    /**
     * Devuelve un conjunto de nombres de paises a los que se les pueden
     * agregar ejercitos.
     */
    fun gastarTarjetasDe(jugador: Int): Iterable<String> {
        val tarjetasNoGastadas = tarjetasDeJugadores.tarjetasDePaisDe(jugador)
                .filter { !tarjetasGastadas[jugador].contains(it) }
        tarjetasGastadas[jugador] += tarjetasNoGastadas
        return tarjetasNoGastadas.map { it.nombre }
    }

    fun marcarTarjetasComoNoGastadas(jugador: Int,
            tarjetas: Iterable<TarjetaDePais>) {
        tarjetasGastadas[jugador] -= tarjetas
    }
}
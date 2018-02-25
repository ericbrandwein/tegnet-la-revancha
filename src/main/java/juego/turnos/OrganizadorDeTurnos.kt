package juego.turnos

import juego.Jugador
import juego.getRandomInt

/**
 * Determina quien es el jugador actual, quien es la mano,
 * y cuando se comienza una nueva vuelta.
 */
class OrganizadorDeTurnos(val jugadores: List<Jugador>,
        var listener: NuevaVueltaListener) {
    var mano: Int = getRandomInt(jugadores.size)
        private set
    var jugadorActual = mano
        private set

    fun jugadorALaIzquierda() =
            (jugadorActual + 1) % jugadores.size

    fun pasarTurno() {
        jugadorActual = jugadorALaIzquierda()
        if (jugadorActual == mano) {
            nuevaVuelta()
        }
    }

    fun nuevaVuelta() {
        jugadorActual = jugadorALaIzquierda()
        mano = jugadorActual
        listener.nuevaVuelta()
    }

    interface NuevaVueltaListener {
        fun nuevaVuelta()
    }

}
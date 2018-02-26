package juego.turnos

import juego.Jugador
import juego.getRandomInt

/**
 * Determina quien es el jugador actual, quien es la mano,
 * y cuando se comienza una nueva vuelta en la fase principal del juego.
 */
class OrganizadorDeTurnosPrincipal(jugadores: List<Jugador>) :
        OrganizadorDeTurnos(jugadores, getRandomInt(jugadores.size)) {

    override fun nuevaVuelta() {
        jugadorActual = jugadorALaIzquierda()
        mano = jugadorActual
        super.nuevaVuelta()
    }
}
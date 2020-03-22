package juego.faseprincipal

import juego.Jugador
import juego.turnos.OrganizadorDeTurnos

/**
 * Determina quien es el jugador actual, quien es la mano,
 * y cuando se comienza una nueva vuelta en la fase principal del juego.
 */
class OrganizadorDeTurnosPrincipal(jugadores: List<Jugador>, mano: Int) :
        OrganizadorDeTurnos(jugadores, mano) {

    override fun nuevaVuelta() {
        jugadorActual = jugadorALaIzquierda()
        mano = jugadorActual
        super.nuevaVuelta()
    }
}
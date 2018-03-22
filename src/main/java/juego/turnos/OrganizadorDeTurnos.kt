package juego.turnos

import juego.Jugador

open class OrganizadorDeTurnos(val jugadores: List<Jugador>, var mano: Int) {
    var jugadorActual = mano
        protected set
    var vueltaActual = 1
        protected set

    fun jugadorALaIzquierda() =
            (jugadorActual + 1) % jugadores.size

    /**
     * Pasa el turno al siguiente jugador
     *
     * @return `true` si pasar el turno hizo que se comienze una nueva vuelta.
     */
    fun pasarTurno() : Boolean {
        jugadorActual = jugadorALaIzquierda()
        if (jugadorActual == mano) {
            vueltaActual++
            nuevaVuelta()
            return true
        }
        return false
    }

    protected open fun nuevaVuelta() {}
}
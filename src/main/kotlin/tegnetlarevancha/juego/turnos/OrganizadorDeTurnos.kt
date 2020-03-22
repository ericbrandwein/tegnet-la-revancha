package juego.turnos

import juego.Jugador

open class OrganizadorDeTurnos(val jugadores: List<Jugador>, var mano: Int) {
    var jugadorActual = mano
        protected set
    var vueltaActual = 1
        protected set
    var primerTurnoDeVuelta = true
        protected set

    fun jugadorALaIzquierda() =
            (jugadorActual + 1) % jugadores.size

    /**
     * Pasa el turno al siguiente jugador
     *
     * @return `true` si pasar el turno hizo que se comience una nueva vuelta.
     */
    fun pasarTurno() : Boolean {
        jugadorActual = jugadorALaIzquierda()
        primerTurnoDeVuelta = jugadorActual == mano
        if (primerTurnoDeVuelta) {
            vueltaActual++
            nuevaVuelta()
        }
        return primerTurnoDeVuelta
    }

    protected open fun nuevaVuelta() {}
}
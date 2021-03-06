package juego.faseincorporacion

import juego.Jugador
import juego.turnos.OrganizadorDeTurnos
import paises.PaisEnJuego

private const val EJERCITOS_PRIMERA_VUELTA = 8
private const val EJERCITOS_SEGUNDA_VUELTA = 4

class EncargadoFaseDeIncorporacion(jugadores: List<Jugador>,
        private val paises: List<PaisEnJuego>, mano: Int,
        private val vista: VistaFaseIncorporacion,
        var listener: TerminarListener) {
    val organizadorDeTurnos: OrganizadorDeTurnos = OrganizadorDeTurnos(
            jugadores, mano)
    var vuelta = 1
        private set

    fun comenzar() {
        vista.encargado = this
        vista.nuevoTurnoDeVueltaDeIncorporacion(incorporadorDePrimeraVuelta())
    }

    fun terminarTurno() {
        if (organizadorDeTurnos.pasarTurno()) {
            vuelta++
        }
        when (vuelta) {
            1 -> vista.nuevoTurnoDeVueltaDeIncorporacion(
                    incorporadorDePrimeraVuelta())
            2 -> vista.nuevoTurnoDeVueltaDeIncorporacion(
                    incorporadorDeSegundaVuelta())
            else -> {
                listener.terminarFase()
            }
        }
    }

    private fun incorporadorDePrimeraVuelta() =
            incorporador(EJERCITOS_PRIMERA_VUELTA)

    private fun incorporadorDeSegundaVuelta() =
            incorporador(EJERCITOS_SEGUNDA_VUELTA)

    private fun incorporador(ejercitos: Int) =
            Incorporador(
                    paises, organizadorDeTurnos.jugadorActual, ejercitos)

    interface TerminarListener {
        fun terminarFase()
    }
}
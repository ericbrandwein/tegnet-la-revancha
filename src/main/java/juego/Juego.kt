package juego

import juego.faseincorporacion.EncargadoFaseDeIncorporacion
import juego.faseprincipal.EncargadoFasePrincipal
import objetivos.Objetivo
import objetivos.listaDeObjetivos
import paises.PaisEnJuego
import paises.listaDePaises

class Juego(val jugadores: List<Jugador>, var vista: VistaJuego) {
    var paises: List<PaisEnJuego> = PaisEnJuego.desdePaises(listaDePaises())
    val mano: Int = getRandomInt(jugadores.size)

    fun comenzar() {
        repartirPaises()
        repartirObjetivos()
        EncargadoFaseDeIncorporacion(
                jugadores, paises, mano, vista.vistaFaseIncorporacion,
                JuegoTerminaPrimeraFaseDeIncorporacionListener()
        ).comenzar()
    }

    fun terminarFaseIncorporacion() {
        EncargadoFasePrincipal(
                paises, jugadores, mano, vista.vistaFasePrincipal,
                object : EncargadoFasePrincipal.GanadoListener {
                    override fun gano(jugador: Int) {
                        println("gano $jugador!")
                    }
                }
        ).comenzar()
    }

    private fun repartirPaises() {
        paises = paises.shuffled()
        for (i in 0 until paises.size / jugadores.size) {
            for (j in 0 until jugadores.size) {
                paises[i + j].dueno = j
            }
        }
        if (jugadores.size == 5) {
            // sobran 2 paises
            val primerJugador = getRandomInt(jugadores.size)
            // como el primer jugador no puede ganar los dos paises,
            // hacemos random entre los otros 4 jugadores
            var segundoJugador = getRandomInt(jugadores.size - 1)
            if (segundoJugador >= primerJugador) segundoJugador++

            paises.last().dueno = segundoJugador
            paises[paises.size - 2].dueno = primerJugador
        }
    }

    private fun repartirObjetivos() {
        var objetivos: List<Objetivo> = listaDeObjetivos().shuffled()
        jugadores.forEach {
            it.objetivo = objetivos.last()
            objetivos = objetivos.dropLast(1)
        }
    }

    private inner class JuegoTerminaPrimeraFaseDeIncorporacionListener :
            EncargadoFaseDeIncorporacion.TerminarListener {
        override fun terminarFase() {
            terminarFaseIncorporacion()
        }
    }
}
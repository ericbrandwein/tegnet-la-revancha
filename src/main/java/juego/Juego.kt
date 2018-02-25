package juego

import juego.fases.Atacador
import juego.fases.FaseDeTurno
import juego.fases.Incorporador
import juego.fases.Reagrupador
import juego.turnos.OrganizadorDeTurnos
import objetivos.Objetivo
import objetivos.listaDeObjetivos
import paises.PaisEnJuego
import paises.listaDePaises

class Juego(val jugadores: List<Jugador>, var vista: JuegoView) {
    var fase: FaseDeTurno = FaseDeTurno.INCORPORACION
    var paises: List<PaisEnJuego> = PaisEnJuego.desdePaises(listaDePaises())
    private val mazoDeSituacion: Mazo<TarjetaDeSituacion> =
            armarMazoDeSituacion()
    var tarjetaDeSituacion: TarjetaDeSituacion? = null
    private val organizadorDeTurnos =
            OrganizadorDeTurnos(jugadores, JuegoNuevaVueltaListener())

    init {
        repartirPaises()
        repartirObjetivos()
        vista.primeraVueltaDeIncorporacion()
        // falta incorporar los primeros paises
    }

    fun terminarVueltaDeIncorporacion() {
        organizadorDeTurnos.nuevaVuelta()
        vista.faseDeIncorporacion(
                Incorporador(paises, organizadorDeTurnos.jugadorActual))
    }

    fun finFaseIncorporacion() {
        fase = FaseDeTurno.ATAQUE
        vista.faseDeAtaque(Atacador(paises, organizadorDeTurnos.jugadorActual))
    }

    fun finFaseAtaque() {
        fase = FaseDeTurno.REAGRUPE
        vista.faseDeReagrupe(
                Reagrupador(paises, organizadorDeTurnos.jugadorActual))
    }

    fun finFaseReagrupe() {
        terminarTurno()
    }

    fun terminarTurno() {
        organizadorDeTurnos.pasarTurno()
        vista.faseDeIncorporacion(
                Incorporador(paises, organizadorDeTurnos.jugadorActual))
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

    private fun sacarTarjetaDeSituacion(): TarjetaDeSituacion {
        tarjetaDeSituacion = mazoDeSituacion.sacarTarjeta()
        return tarjetaDeSituacion as TarjetaDeSituacion
    }

    private inner class JuegoNuevaVueltaListener :
            OrganizadorDeTurnos.NuevaVueltaListener {
        override fun nuevaVuelta() {
            sacarTarjetaDeSituacion()
        }
    }
}
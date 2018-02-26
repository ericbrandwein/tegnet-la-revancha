package juego

import juego.etapas.Atacador
import juego.etapas.EtapaDeTurno
import juego.etapas.Incorporador
import juego.etapas.Reagrupador
import juego.turnos.OrganizadorDeTurnosPrincipal
import objetivos.Objetivo
import objetivos.listaDeObjetivos
import paises.PaisEnJuego
import paises.cantPaisesConDueno
import paises.listaDePaises

class Juego(val jugadores: List<Jugador>, var vista: JuegoView) {
    var etapa: EtapaDeTurno = EtapaDeTurno.INCORPORACION
    var paises: List<PaisEnJuego> = PaisEnJuego.desdePaises(listaDePaises())
    private val mazoDeSituacion: Mazo<TarjetaDeSituacion> =
            armarMazoDeSituacion()
    var tarjetaDeSituacion: TarjetaDeSituacion? = null
    private val organizadorDeTurnos = OrganizadorDeTurnosPrincipal(jugadores)

    init {
        repartirPaises()
        repartirObjetivos()
        vista.primeraFaseDeIncorporacion(
                EncargadoPrimeraFaseDeIncorporacion(
                        jugadores, paises, organizadorDeTurnos.mano, vista,
                        JuegoTerminaPrimeraFaseDeIncorporacionListener()
                )
        )
    }

    fun terminarPrimeraFaseIncorporacion() {
        comienzoEtapaIncorporacion()
    }

    fun comienzoEtapaIncorporacion() {
        vista.etapaDeIncorporacion(
                Incorporador(
                        paises, organizadorDeTurnos.jugadorActual,
                        ejercitosIncorporablesEnTurno()
                )
        )
    }

    fun finEtapaIncorporacion() {
        etapa = EtapaDeTurno.ATAQUE
        vista.etapaDeAtaque(Atacador(paises, organizadorDeTurnos.jugadorActual))
    }

    fun finEtapaAtaque() {
        etapa = EtapaDeTurno.REAGRUPE
        vista.etapaDeReagrupe(
                Reagrupador(paises, organizadorDeTurnos.jugadorActual))
    }

    fun finEtapaReagrupe() {
        terminarTurno()
    }

    fun terminarTurno() {
        if (organizadorDeTurnos.pasarTurno()) {
            sacarTarjetaDeSituacion()
        }
        comienzoEtapaIncorporacion()
    }

    fun ejercitosIncorporablesEnTurno() =
            cantPaisesConDueno(paises, organizadorDeTurnos.jugadorActual) / 2

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

    private inner class JuegoTerminaPrimeraFaseDeIncorporacionListener :
            EncargadoPrimeraFaseDeIncorporacion.TerminarListener {
        override fun terminarFase() {
            terminarPrimeraFaseIncorporacion()
        }
    }
}
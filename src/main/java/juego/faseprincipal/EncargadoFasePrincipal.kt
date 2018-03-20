package juego.faseprincipal

import juego.Jugador
import juego.faseprincipal.canjes.Canjeador
import juego.faseprincipal.etapas.EncargadoEtapaIncorporacion
import juego.faseprincipal.etapas.EncargadoEtapaReagrupe
import juego.faseprincipal.etapas.EtapaDeTurno
import juego.faseprincipal.etapas.ataque.EncargadoEtapaAtaque
import juego.faseprincipal.situacion.TarjetaDeSituacion
import juego.faseprincipal.situacion.armarMazoDeSituacion
import juego.faseprincipal.tarjetasconsimbolos.TarjetasDeJugadores
import juego.mazo.MazoMezclante
import paises.PaisEnJuego

class EncargadoFasePrincipal(val paises: List<PaisEnJuego>,
        val jugadores: List<Jugador>, mano: Int,
        private val vista: VistaFasePrincipal,
        var listener: GanadoListener) {
    var etapa: EtapaDeTurno = EtapaDeTurno.INCORPORACION
    private val mazoDeSituacion: MazoMezclante<TarjetaDeSituacion> =
            armarMazoDeSituacion()
    var tarjetaDeSituacion: TarjetaDeSituacion = mazoDeSituacion.sacarTarjeta()
        private set
    private var encargadoEtapaAtaqueActual: EncargadoEtapaAtaque? = null
    private val organizadorDeTurnos =
            OrganizadorDeTurnosPrincipal(jugadores, mano)
    private val tarjetasDeJugadores = TarjetasDeJugadores(jugadores.size)
    private val canjeador = Canjeador(jugadores.size, tarjetasDeJugadores)


    fun comenzar() {
        // TODO: hacer que si alguien tiene ya un continente desde el principio
        // se le de la tarjeta de continente.
        vista.encargadoFase = this
        comienzoEtapaIncorporacion()
    }

    private fun comienzoEtapaIncorporacion() {
        vista.etapaDeIncorporacion(
                EncargadoEtapaIncorporacion(
                        organizadorDeTurnos.jugadorActual, paises,
                        tarjetasDeJugadores, canjeador)
        )
    }

    fun finEtapaIncorporacion() {
        chequearEtapa(EtapaDeTurno.INCORPORACION)
        comienzoEtapaAtaque()
    }

    private fun comienzoEtapaAtaque() {
        etapa = EtapaDeTurno.ATAQUE
        val jugadorActual = organizadorDeTurnos.jugadorActual
        encargadoEtapaAtaqueActual = EncargadoEtapaAtaque(
                paises, jugadores, jugadorActual,
                tarjetasDeJugadores,
                object : EncargadoEtapaAtaque.GanadoListener {
                    override fun gano() {
                        listener.gano(jugadorActual)
                    }
                }, vista.vistaEtapaAtaque, tarjetaDeSituacion)
        vista.etapaDeAtaque(encargadoEtapaAtaqueActual!!)
    }

    fun finEtapaAtaque() {
        chequearEtapa(EtapaDeTurno.ATAQUE)
        comienzoEtapaReagrupe()
    }

    private fun comienzoEtapaReagrupe() {
        etapa = EtapaDeTurno.REAGRUPE
        vista.etapaDeReagrupe(
                EncargadoEtapaReagrupe(
                        paises, organizadorDeTurnos.jugadorActual))
    }

    fun finEtapaReagrupe() {
        chequearEtapa(EtapaDeTurno.REAGRUPE)
        terminarTurno()
    }

    private fun terminarTurno() {
        if (puedeSacarTarjetaDePais()) {
            sacarTarjetaDePais()
        }
        encargadoEtapaAtaqueActual = null
        if (organizadorDeTurnos.pasarTurno()) {
            sacarTarjetaDeSituacion()
        }
        comienzoEtapaIncorporacion()
    }

    private fun puedeSacarTarjetaDePais() =
            encargadoEtapaAtaqueActual != null && encargadoEtapaAtaqueActual!!.paisesConquistados > 0

    private fun sacarTarjetaDePais() {
        tarjetasDeJugadores.sacarTarjetaDePais(
                organizadorDeTurnos.jugadorActual)
    }

    private fun chequearEtapa(etapaNecesaria: EtapaDeTurno) {
        if (etapa != etapaNecesaria)
            throw EtapaEquivocadaException(etapa, etapaNecesaria)
    }

    private fun sacarTarjetaDeSituacion() {
        tarjetaDeSituacion = mazoDeSituacion.sacarTarjeta()
    }

    interface GanadoListener {
        fun gano(jugador: Int)
    }

    class EtapaEquivocadaException(etapaActual: EtapaDeTurno,
            etapaEsperada: EtapaDeTurno) :
            IllegalStateException(
                    "Etapa equivocada: Se estaba en " +
                            "etapa $etapaActual, pero debería haber estado en " +
                            "etapa $etapaEsperada para poder hacer esto.")
}
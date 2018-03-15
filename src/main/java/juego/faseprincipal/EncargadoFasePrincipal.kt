package juego.faseprincipal

import juego.Jugador
import juego.mazo.MazoMezclante
import juego.faseprincipal.etapas.EtapaDeTurno
import juego.faseprincipal.etapas.Incorporador
import juego.faseprincipal.etapas.Reagrupador
import juego.faseprincipal.etapas.ataque.Atacador
import juego.faseprincipal.situacion.TarjetaDeSituacion
import juego.faseprincipal.situacion.armarMazoDeSituacion
import juego.faseprincipal.tarjetasdepaises.TarjetasDeJugadores
import paises.PaisEnJuego
import paises.cantPaisesConDueno

class EncargadoFasePrincipal(val paises: List<PaisEnJuego>,
        val jugadores: List<Jugador>, mano: Int,
        private val vista: VistaFasePrincipal,
        var listener: GanadoListener) {
    var etapa: EtapaDeTurno = EtapaDeTurno.INCORPORACION
    private val mazoDeSituacion: MazoMezclante<TarjetaDeSituacion> =
            armarMazoDeSituacion()
    var tarjetaDeSituacion: TarjetaDeSituacion = mazoDeSituacion.sacarTarjeta()
        private set
    private var atacadorActual: Atacador? = null
    private val organizadorDeTurnos =
            OrganizadorDeTurnosPrincipal(jugadores, mano)
    private val tarjetasDeJugadores =
            TarjetasDeJugadores(jugadores.size, paises)

    init {
        vista.encargado = this
        comienzoEtapaIncorporacion()
    }

    private fun comienzoEtapaIncorporacion() {
        vista.etapaDeIncorporacion(
                Incorporador(
                        paises, organizadorDeTurnos.jugadorActual,
                        ejercitosIncorporablesEnTurno()
                )
        )
    }

    fun finEtapaIncorporacion() {
        chequearEtapa(EtapaDeTurno.INCORPORACION)
        comienzoEtapaAtaque()
    }

    private fun comienzoEtapaAtaque() {
        etapa = EtapaDeTurno.ATAQUE
        val jugadorActual = organizadorDeTurnos.jugadorActual
        atacadorActual = Atacador(
                paises, jugadores, jugadorActual,
                object : Atacador.GanadoListener {
                    override fun gano() {
                        listener.gano(jugadorActual)
                    }
                }, vista.vistaEtapaAtaque, tarjetaDeSituacion)
        vista.etapaDeAtaque(atacadorActual!!)
    }

    fun finEtapaAtaque() {
        chequearEtapa(EtapaDeTurno.ATAQUE)
        comienzoEtapaReagrupe()
    }

    private fun comienzoEtapaReagrupe() {
        etapa = EtapaDeTurno.REAGRUPE
        vista.etapaDeReagrupe(
                Reagrupador(paises, organizadorDeTurnos.jugadorActual))
    }

    fun finEtapaReagrupe() {
        chequearEtapa(EtapaDeTurno.REAGRUPE)
        terminarTurno()
    }

    private fun terminarTurno() {
        if (puedeSacarTarjetaDePais()) {
            sacarTarjetaDePais()
        }
        atacadorActual = null
        if (organizadorDeTurnos.pasarTurno()) {
            sacarTarjetaDeSituacion()
        }
        comienzoEtapaIncorporacion()
    }

    private fun puedeSacarTarjetaDePais() =
            atacadorActual != null && atacadorActual!!.paisesConquistados > 0

    private fun sacarTarjetaDePais() {
        tarjetasDeJugadores.sacarTarjeta(organizadorDeTurnos.jugadorActual)
    }

    private fun chequearEtapa(etapaNecesaria: EtapaDeTurno) {
        if (etapa != etapaNecesaria)
            throw EtapaEquivocadaException(etapa, etapaNecesaria)
    }

    private fun ejercitosIncorporablesEnTurno() =
            cantPaisesConDueno(paises, organizadorDeTurnos.jugadorActual) / 2

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
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
import paises.continentesOcupados

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

    private val gastadorDeTarjetasDePais =
            GastadorDeTarjetasDePais(jugadores.size, tarjetasDeJugadores)

    /**
     * Cantidad de ejercitos que se agregan a un pais cuando se gasta
     * una tarjeta.
     */
    private val EJERCITOS_AGREGADOS_POR_TARJETA = 4

    private val jugadorActual
        get() = organizadorDeTurnos.jugadorActual

    fun comenzar() {
        darTarjetasDeContinente()
        vista.encargadoFase = this
        comenzarTurno()
    }

    /**
     * Entrega tarjetas de continente a los jugadores a los que les corresponda.
     */
    private fun darTarjetasDeContinente() {
        (1..jugadores.size)
                .map { continentesOcupados(paises, it) }
                .forEachIndexed { index, continentes ->
                    continentes.forEach { continente ->
                        tarjetasDeJugadores.entregarTarjetaDeContinente(
                                index, continente)
                    }
                }
    }

    private fun comenzarTurno() {
        if (organizadorDeTurnos.vueltaActual == 1) {
            comienzoEtapaAtaque()
        } else {
            comienzoEtapaIncorporacion()
        }
    }

    private fun comienzoEtapaIncorporacion() {
        etapa = EtapaDeTurno.INCORPORACION
        vista.etapaDeIncorporacion(
                EncargadoEtapaIncorporacion(
                        jugadorActual, paises, tarjetasDeJugadores, canjeador,
                        gastadorDeTarjetasDePais)
        )
    }

    fun finEtapaIncorporacion() {
        chequearEtapa(EtapaDeTurno.INCORPORACION)
        comienzoEtapaAtaque()
    }

    private fun comienzoEtapaAtaque() {
        etapa = EtapaDeTurno.ATAQUE
        encargadoEtapaAtaqueActual = EncargadoEtapaAtaque(
                paises, jugadores, jugadorActual,
                tarjetasDeJugadores, canjeador,
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
        vista.etapaDeReagrupe(EncargadoEtapaReagrupe(paises, jugadorActual))
    }

    fun finEtapaReagrupe() {
        chequearEtapa(EtapaDeTurno.REAGRUPE)
        terminarTurno()
    }

    private fun terminarTurno() {
        if (puedeSacarTarjetaDePais()) {
            sacarTarjetaDePais()
        }
        agregarEjercitosPorTarjetas()
        encargadoEtapaAtaqueActual = null
        if (organizadorDeTurnos.pasarTurno()) {
            sacarTarjetaDeSituacion()
        }

        comenzarTurno()
    }

    /**
     * Agrega [EJERCITOS_AGREGADOS_POR_TARJETA] ejercitos a los paises
     * para los que el jugador actual tenga las tarjetas,
     * si es que no se agregaron todavia.
     */
    private fun agregarEjercitosPorTarjetas() {
        val nombresPaises =
                gastadorDeTarjetasDePais.gastarTarjetasDe(jugadorActual)
        paises.filter { it.pais.nombre in nombresPaises }
                .forEach { it.ejercitos += EJERCITOS_AGREGADOS_POR_TARJETA }
    }

    private fun puedeSacarTarjetaDePais() =
            encargadoEtapaAtaqueActual != null && encargadoEtapaAtaqueActual!!.paisesConquistados > 0

    private fun sacarTarjetaDePais() {
        tarjetasDeJugadores.sacarTarjetaDePais(jugadorActual)
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
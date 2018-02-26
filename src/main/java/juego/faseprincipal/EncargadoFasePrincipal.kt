package juego.faseprincipal

import juego.Jugador
import juego.Mazo
import juego.faseprincipal.etapas.Atacador
import juego.faseprincipal.etapas.EtapaDeTurno
import juego.faseprincipal.etapas.Incorporador
import juego.faseprincipal.etapas.Reagrupador
import juego.faseprincipal.situacion.TarjetaDeSituacion
import juego.faseprincipal.situacion.armarMazoDeSituacion
import paises.PaisEnJuego
import paises.cantPaisesConDueno

class EncargadoFasePrincipal(val paises: List<PaisEnJuego>,
        val jugadores: List<Jugador>, mano: Int,
        private val vista: VistaFasePrincipal,
        var listener: GanadoListener) {
    var etapa: EtapaDeTurno = EtapaDeTurno.INCORPORACION
    private val mazoDeSituacion: Mazo<TarjetaDeSituacion> =
            armarMazoDeSituacion()
    var tarjetaDeSituacion: TarjetaDeSituacion = mazoDeSituacion.sacarTarjeta()
        private set
    private val organizadorDeTurnos =
            OrganizadorDeTurnosPrincipal(jugadores, mano)

    init {
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
        vista.etapaDeAtaque(Atacador(paises, organizadorDeTurnos.jugadorActual))
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
        if (organizadorDeTurnos.pasarTurno()) {
            sacarTarjetaDeSituacion()
        }
        comienzoEtapaIncorporacion()
    }

    private fun chequearEtapa(etapaNecesaria: EtapaDeTurno) {
        if (etapa != etapaNecesaria)
            throw EtapaEquivocadaException(etapa, etapaNecesaria)
    }

    private fun ejercitosIncorporablesEnTurno() =
            cantPaisesConDueno(paises, organizadorDeTurnos.jugadorActual) / 2

    private fun sacarTarjetaDeSituacion(): TarjetaDeSituacion {
        tarjetaDeSituacion = mazoDeSituacion.sacarTarjeta()
        return tarjetaDeSituacion
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
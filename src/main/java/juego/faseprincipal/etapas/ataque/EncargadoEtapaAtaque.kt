package juego.faseprincipal.etapas.ataque

import juego.Jugador
import juego.faseprincipal.canjes.Canjeador
import juego.faseprincipal.situacion.TarjetaDeSituacion
import juego.faseprincipal.tarjetasconsimbolos.TarjetasDeJugadores
import paises.*
import kotlin.math.min

/**
 * Encargado de realizar las acciones necesarias en la [EtapaDeTurno.ATAQUE].
 */
class EncargadoEtapaAtaque(private val paises: List<PaisEnJuego>,
        private val jugadores: List<Jugador>,
        private val jugador: Int,
        private val tarjetasDeJugadores: TarjetasDeJugadores,
        private val canjeador: Canjeador,
        private val listener: GanadoListener,
        private val vista: VistaEtapaAtaque,
        private val situacion: TarjetaDeSituacion) {
    var paisesConquistados = 0
        private set
    private var ultimoAtaque: Pair<PaisEnJuego, PaisEnJuego>? = null
    private var ultimoDueno: Int? = null

    fun paisesAtacablesDesde(nombrePais: String): List<PaisEnJuego> {
        val pais = paisConNombre(paises, nombrePais)
        val conectaCon = pais.pais.conectaCon
        return paises.filter {
            it.pais.nombre in conectaCon && it.dueno != jugador
        }
    }

    /**
     * Ataca desde el [atacante] hacia el [atacado].
     *
     * El ataque debe ser posible de hacer.
     *
     * @see paisesAtacablesDesde
     */
    fun atacar(atacante: String, atacado: String) {
        val paisAtacante = paisConNombre(paises, atacante)
        val paisAtacado = paisConNombre(paises, atacado)
        batallar(paisAtacante, paisAtacado, situacion)
        val conquistado = paisAtacado.ejercitos == 0
        if (conquistado) {
            conquistar(paisAtacante, paisAtacado)
        }
    }

    private fun conquistar(atacante: PaisEnJuego, atacado: PaisEnJuego) {
        ultimoDueno = atacado.dueno
        if (jugadores[jugador].objetivo!!.cumplido(
                        paises, atacado, jugadores, jugador)) {
            listener.gano()
        }
        ultimoAtaque = atacante to atacado
        vista.elegidorDeEjercitosQuePasar()
    }

    fun pasarEjercitos(ejercitos: Int) {
        ultimoAtaque!!.first.ejercitos -= ejercitos
        val atacado = ultimoAtaque!!.second
        atacado.ejercitos += ejercitos
        if (perdio(ultimoDueno!!)) {
            jugadores[ultimoDueno!!].perdio = true
            vista.perdio(ultimoDueno!!)
        }
        ultimoDueno = null

        val continente = atacado.pais.continente
        // Si conquisto el continente, hay que darle tarjeta de continente.
        if (conquistoContinente() &&
                !canjeador.canjeoTarjetaDeContinente(jugador, continente)) {

            tarjetasDeJugadores.entregarTarjetaDeContinente(
                    jugador, continente)
        }

        // Si el otro perdio el continente, tiene que devolver la tarjeta.
        if (perdioContinente()) {
            val tarjetaDeContinente =
                    tarjetaDeContinenteDe(atacado.dueno, continente)
            tarjetasDeJugadores.devolverTarjetasDeContinente(
                    setOf(tarjetaDeContinente))
        }
        ultimoAtaque = null

        atacado.dueno = jugador
        paisesConquistados++
    }

    private fun tarjetaDeContinenteDe(jugador: Int, continente: Continente) =
            tarjetasDeJugadores.tarjetasDeContinenteDe(jugador)
                    .first { it.continente == continente }

    private fun conquistoContinente(): Boolean {
        val conquistado = ultimoAtaque!!.second
        val continente = conquistado.pais.continente
        return continenteConquistado(paises, continente, jugador, conquistado)
    }

    private fun perdioContinente(): Boolean {
        val conquistado = ultimoAtaque!!.second
        val continente = conquistado.pais.continente
        return cantPaisesDeContinenteConDueno(
                paises, continente, conquistado.dueno) ==
                paisesDeContinente(paises, continente).size
    }


    fun puedenPasarseDesde(atacante: PaisEnJuego): Int =
            min(atacante.ejercitos - 1, 3)

    private fun perdio(jugador: Int) = cantPaisesConDueno(paises, jugador) == 0

    interface GanadoListener {
        /**
         * Se llama cuando el jugador actual ganó.
         */
        fun gano()
    }
}
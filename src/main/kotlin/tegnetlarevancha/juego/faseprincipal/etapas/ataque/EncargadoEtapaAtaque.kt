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
        ultimoAtaque = atacante to atacado
        vista.elegidorDeEjercitosQuePasar(::pasarEjercitos)
    }

    private fun pasarEjercitos(ejercitos: Int) {
        val (paisAtacante, paisAtacado) = ultimoAtaque!!
        ultimoAtaque = null
        paisAtacante.ejercitos -= ejercitos
        paisAtacado.ejercitos += ejercitos

        val jugadorAtacado = paisAtacado.dueno
        paisAtacado.dueno = jugador
        paisesConquistados++

        val continente = paisAtacado.pais.continente
        // Si el otro perdio el continente, tiene que devolver la tarjeta.
        if (perdioContinente(continente, jugadorAtacado)) {
            val tarjetaDeContinente =
                    tarjetaDeContinenteDe(jugadorAtacado, continente)
            tarjetasDeJugadores.devolverTarjetasDeContinente(
                    setOf(tarjetaDeContinente))
        }

        // Si conquisto el continente, hay que darle tarjeta de continente.
        if (conquistoContinente(continente) &&
                !canjeador.canjeoTarjetaDeContinente(jugador, continente)) {
            tarjetasDeJugadores.entregarTarjetaDeContinente(jugador, continente)
        }

        if (perdio(jugadorAtacado)) {
            jugadores[jugadorAtacado].perdio = true
            // TODO: darle las tarjetas del jugador que perdio al jugador que
            // lo conquisto.
            vista.perdio(jugadorAtacado)
        }

        if (jugadores[jugador].objetivo!!.cumplido(
                        paises, jugadores, jugador, jugadorAtacado)) {
            listener.gano()
        }
    }

    private fun tarjetaDeContinenteDe(jugador: Int, continente: Continente) =
            tarjetasDeJugadores.tarjetasDeContinenteDe(jugador)
                    .first { it.continente == continente }

    private fun conquistoContinente(continente: Continente) =
            continenteOcupado(paises, continente, jugador)

    /**
     * Determina si el [jugador] perdio el [continente] luego de que le hayan
     * conquistado un pais de dicho [continente].
     */
    private fun perdioContinente(continente: Continente, jugador: Int) =
            /*
            Sabemos que le conquistaron un pais del continente.
            Por lo tanto, si la cantidad de paises de ese continente
            que el posee es igual a la cantidad de paises en el continente
            menos el pais perdido, habra perdido el continente.
             */
            cantPaisesDeContinenteConDueno(paises, continente, jugador) ==
                    paisesDeContinente(paises, continente).size - 1


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
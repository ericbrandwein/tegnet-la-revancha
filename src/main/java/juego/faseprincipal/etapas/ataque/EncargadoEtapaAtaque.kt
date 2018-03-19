package juego.faseprincipal.etapas.ataque

import juego.Jugador
import juego.faseprincipal.situacion.TarjetaDeSituacion
import paises.PaisEnJuego
import paises.cantPaisesConDueno
import paises.paisConNombre
import kotlin.math.min

/**
 * Encargado de realizar las acciones necesarias en la [EtapaDeTurno.ATAQUE].
 */
class EncargadoEtapaAtaque(val paises: List<PaisEnJuego>, val jugadores: List<Jugador>,
        val jugador: Int, private val listener: GanadoListener,
        private val vista: VistaEtapaAtaque,
        private val situacion: TarjetaDeSituacion) {
    var paisesConquistados = 0

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
        val antiguoDueno = atacado.dueno
        if (jugadores[jugador].objetivo!!.cumplido(
                        paises, atacado, jugadores, jugador)) {
            listener.gano()
        }
        atacado.dueno = jugador
        paisesConquistados++
        val pasar = vista.cuantosPasar(atacante, atacado)
        atacante.ejercitos -= pasar
        atacado.ejercitos += pasar
        if (perdio(antiguoDueno)) {
            jugadores[antiguoDueno].perdio = true
            vista.perdio(antiguoDueno)
        }
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
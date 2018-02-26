package juego.etapas

import paises.PaisEnJuego
import paises.paisConNombre
import kotlin.math.min

/**
 * Encargado de realizar las acciones necesarias en la [EtapaDeTurno.ATAQUE].
 */
class Atacador(val paises: List<PaisEnJuego>, val jugador: Int) {
    var paisesConquistados = 0
    var elegidor: ElegidorDeEjercitosQuePasar? = null

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
        batallar(paisAtacante, paisAtacado)
        val conquistado = paisAtacado.ejercitos == 0
        if (conquistado) {
            conquistar(paisAtacante, paisAtacado)
        }
    }

    private fun conquistar(atacante: PaisEnJuego, atacado: PaisEnJuego) {
        atacado.dueno = jugador
        paisesConquistados++
        var pasar = 1
        if (elegidor != null) {
            pasar = elegidor!!.cuantosPasar(atacante, atacado)
        }
        atacante.ejercitos -= pasar
        atacado.ejercitos += pasar
    }

    fun puedenPasarseDesde(atacante: PaisEnJuego): Int =
            min(atacante.ejercitos - 1, 3)

    interface ElegidorDeEjercitosQuePasar {
        /**
         * Es llamado cuando se conquista el pais [atacado].
         * Determina cuantos ejercitos se deberían pasar.
         *
         * @see puedenPasarseDesde
         */
        fun cuantosPasar(atacante: PaisEnJuego, atacado: PaisEnJuego): Int
    }
}
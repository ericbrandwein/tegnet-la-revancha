package juego.fases

import paises.PaisEnJuego
import paises.paisConNombre

/**
 * Encargado de realizar las acciones necesarias en la [FaseDeTurno.REAGRUPE].
 */
class Reagrupador(val paises: List<PaisEnJuego>, val jugador: Int) {
    val ejercitosTrabados = mutableMapOf<PaisEnJuego, Int>()

    fun paisesReagrupablesDesde(desde: String): List<PaisEnJuego> {
        val paisDesde = paisConNombre(paises, desde)
        val conectados = paisDesde.pais.conectaCon
        return paises.filter {
            it.pais.nombre in conectados && it.dueno == jugador
        }
    }

    fun cuantosPuedeReagrupar(desde: String): Int {
        val paisDesde = paisConNombre(paises, desde)
        var puedeReagrupar = paisDesde.ejercitos - 1
        if (paisDesde in ejercitosTrabados) {
            puedeReagrupar -= ejercitosTrabados[paisDesde] as Int
        }
        return puedeReagrupar
    }

    /**
     * Reagrupa [ejercitos] desde [desde] hacia [hacia].
     *
     * @param desde debe ser un [Pais.nombre] que pertenezca al [jugador]
     * @param hacia otro pais [al que se pueda reagrupar]
     * [paisesReagrupablesDesde]
     * @param ejercitos una cantidad de ejercitos [que se puedan pasar]
     * [cuantosPuedeReagrupar]
     */
    fun reagrupar(desde: String, hacia: String, ejercitos: Int) {
        val paisDesde = paisConNombre(paises, desde)
        val paisHacia = paisConNombre(paises, hacia)
        paisDesde.ejercitos -= ejercitos
        paisHacia.ejercitos += ejercitos
        if (paisHacia in ejercitosTrabados) {
            ejercitosTrabados[paisHacia] = ejercitosTrabados[paisHacia] as Int +
                    ejercitos
        } else {
            ejercitosTrabados[paisHacia] = ejercitos
        }
    }
}
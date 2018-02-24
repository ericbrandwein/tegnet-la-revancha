package juego.fases

import paises.PaisEnJuego

class ReagrupeManager() {
    val ejercitosTrabados = mutableMapOf<PaisEnJuego, Int>()

    fun cuantosPuedeReagrupar(desde: PaisEnJuego, hacia: PaisEnJuego): Int {
        var puedeReagrupar = desde.ejercitos - 1
        if (desde in ejercitosTrabados) {
             puedeReagrupar -= ejercitosTrabados[desde] as Int
        }
        return puedeReagrupar
    }

    fun reagrupar(desde: PaisEnJuego, hacia: PaisEnJuego, ejercitos: Int) {
        desde.ejercitos -= ejercitos
        hacia.ejercitos += ejercitos
        if (hacia in ejercitosTrabados) {
            ejercitosTrabados[hacia] = ejercitosTrabados[hacia] as Int + ejercitos
        } else {
            ejercitosTrabados[hacia] = ejercitos
        }
    }
}
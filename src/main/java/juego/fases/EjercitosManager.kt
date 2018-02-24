package juego.fases

import paises.PaisEnJuego

class EjercitosManager(val paises: List<PaisEnJuego>) {
    private var reagrupador = ReagrupeManager()

    fun agregarEjercitos(pais: String, ejercitos: Int) {
        paisConNombre(pais).ejercitos += ejercitos
    }

    fun sacarEjercitos(pais: String, ejercitos: Int) =
            agregarEjercitos(pais, -ejercitos)

    /**
     * Devuelve `true` si el pais [atacado] fue conquistado.
     */
    fun atacar(atacante: String, atacado: String): Boolean {
        val paisAtacante = paisConNombre(atacante)
        val paisAtacado = paisConNombre(atacado)
        batallar(paisAtacante, paisAtacado)
        return paisAtacado.ejercitos == 0
    }

    fun cuantosPuedeReagrupar(desde: String, hacia: String): Int {
        val paisDesde = paisConNombre(desde)
        val paisHacia = paisConNombre(hacia)
        return reagrupador.cuantosPuedeReagrupar(paisDesde, paisHacia)
    }

    fun reagrupar(desde: String, hacia: String, ejercitos: Int) {
        val paisDesde = paisConNombre(desde)
        val paisHacia = paisConNombre(hacia)
        reagrupador.reagrupar(paisDesde, paisHacia, ejercitos)
    }

    fun resetearReagrupe() {
        reagrupador = ReagrupeManager()
    }

    private fun paisConNombre(pais: String) =
            paises.first { it.pais.nombre == pais }
}
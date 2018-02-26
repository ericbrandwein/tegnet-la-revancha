package juego.faseprincipal.etapas

import paises.PaisEnJuego
import paises.paisConNombre

class Incorporador(private val paises: List<PaisEnJuego>,
        val jugador: Int, ejercitosDisponibles: Int) {
    var ejercitosRestantesAIncorporar = ejercitosDisponibles
        protected set

    /**
     * Incorpora [ejercitos] en el pais con [nombrePais].
     *
     * @param nombrePais Debe ser un pais que pertenezca al [jugador]
     * @param ejercitos Debe ser menor o igual a los
     * [ejercitosRestantesAIncorporar]
     */
    fun incorporar(nombrePais: String, ejercitos: Int) {
        val pais = paisConNombre(paises, nombrePais)
        pais.ejercitos += ejercitos
        ejercitosRestantesAIncorporar -= ejercitos
    }
}
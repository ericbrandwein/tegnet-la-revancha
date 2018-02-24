package juego.fases

import paises.PaisEnJuego
import paises.cantPaisesConDueno
import paises.paisConNombre

class Incorporador(val paises: List<PaisEnJuego>, val jugador: Int) {
    var ejercitosRestantesAIncorporar = cantPaisesConDueno(paises, jugador) / 2
        private set

    fun incorporar(nombrePais: String, ejercitos: Int) {
        val pais = paisConNombre(paises, nombrePais)
        pais.ejercitos += ejercitos
        ejercitosRestantesAIncorporar -= ejercitos
    }
}
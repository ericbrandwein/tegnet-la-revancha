package objetivos

import paises.Continente
import paises.PaisEnJuego

fun cantPaisesConDueno(paises: List<PaisEnJuego>, jugador: Int) =
        paises.count { it.dueno == jugador }

/**
 * Determina si, con el nuevo [paisConquistado],
 * el [jugador] ocupa el [continente].
 */
fun continenteConquistado(paises: List<PaisEnJuego>, jugador: Int,
                          continente: Continente,
                          paisConquistado: PaisEnJuego): Boolean {
    val paisesDeContinente = paises.filter { it.pais.continente == continente }
    val cantPaisesOcupadosDeContinente = paisesDeContinente.count {
        it.dueno == jugador || it.pais == paisConquistado.pais
    }
    return cantPaisesOcupadosDeContinente == paisesDeContinente.size
}
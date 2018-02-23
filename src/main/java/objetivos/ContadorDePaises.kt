package objetivos

import paises.Continente
import paises.PaisEnJuego

fun cantPaisesConDueno(paises: List<PaisEnJuego>, jugador: Int) =
        paises.count { it.dueno == jugador }

private fun paisesDeContinente(paises: List<PaisEnJuego>,
        continente: Continente) =
        paises.filter { it.pais.continente == continente }

/**
 * Determina la cantidad de paises que ocupa en el [continente] el [jugador],
 * incluyendo el nuevo [paisConquistado].
 */
fun cantPaisesDeContinenteConDueno(paises: List<PaisEnJuego>,
        continente: Continente, jugador: Int,
        paisConquistado: PaisEnJuego): Int {
    val paisesDeContinente = paisesDeContinente(paises, continente)
    var cantPaisesDeJugadorEnContinente =
            cantPaisesConDueno(paisesDeContinente, jugador)
    if (paisConquistado.pais.continente == continente) {
        cantPaisesDeJugadorEnContinente++
    }
    return cantPaisesDeJugadorEnContinente
}

/**
 * Determina si, con el nuevo [paisConquistado],
 * el [jugador] ocupa el [continente].
 */
fun continenteConquistado(paises: List<PaisEnJuego>, continente: Continente,
        jugador: Int, paisConquistado: PaisEnJuego): Boolean {
    val cantPaisesDeJugadorEnContinente = cantPaisesDeContinenteConDueno(
            paises, continente, jugador, paisConquistado
    )
    val cantPaisesEnContinente = paisesDeContinente(paises, continente).size
    return cantPaisesDeJugadorEnContinente == cantPaisesEnContinente
}

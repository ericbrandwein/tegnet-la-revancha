package paises

fun paisConNombre(paises: List<PaisEnJuego>, nombre: String) =
        paises.first { it.pais.nombre == nombre }

fun cantPaisesConDueno(paises: Iterable<PaisEnJuego>, jugador: Int) =
        paises.count { it.dueno == jugador }

fun paisesDeContinente(paises: Iterable<PaisEnJuego>,
        continente: Continente) =
        paises.filter { it.pais.continente == continente }

fun cantPaisesDeContinenteConDueno(paises: Iterable<PaisEnJuego>,
        continente: Continente, jugador: Int): Int =
        cantPaisesConDueno(paisesDeContinente(paises, continente), jugador)

/**
 * Determina la cantidad de paises que ocupa en el [continente] el [jugador],
 * incluyendo el nuevo [paisConquistado].
 */
fun cantPaisesDeContinenteConDuenoMasConquistado(paises: Iterable<PaisEnJuego>,
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
    val cantPaisesDeJugadorEnContinente =
            cantPaisesDeContinenteConDuenoMasConquistado(
                    paises, continente, jugador, paisConquistado
            )
    val cantPaisesEnContinente = paisesDeContinente(paises, continente).size
    return cantPaisesDeJugadorEnContinente == cantPaisesEnContinente
}
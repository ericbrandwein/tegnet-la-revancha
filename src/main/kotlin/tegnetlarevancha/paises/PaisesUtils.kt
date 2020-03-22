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
 * Determina si el [jugador] ocupa todos los paises del [continente].
 */
fun continenteOcupado(paises: Iterable<PaisEnJuego>, continente: Continente,
        jugador: Int): Boolean {
    val cantPaisesDeJugadorEnContinente =
            cantPaisesDeContinenteConDueno(paises, continente, jugador)
    val cantPaisesEnContinente = paisesDeContinente(paises, continente).size
    return cantPaisesDeJugadorEnContinente == cantPaisesEnContinente
}

fun continentesOcupados(paises: Iterable<PaisEnJuego>, jugador: Int) =
        Continente.values().filter { continenteOcupado(paises, it, jugador) }

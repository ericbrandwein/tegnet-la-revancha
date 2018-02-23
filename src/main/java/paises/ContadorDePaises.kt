package paises

fun cantPaisesConDueno(paises: List<PaisEnJuego>, jugador: Int) =
        paises.count { it.dueno == jugador }

package paises

class PaisEnJuego(val pais: Pais) {
    var dueno = 0
    var ejercitos = 1
    var misiles = 1

    companion object {
        fun desdePaises(paises: List<Pais>): List<PaisEnJuego> =
                paises.map { PaisEnJuego(it) }
    }
}

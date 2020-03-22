package juego.mazo

/**
 * Mazo que se vuelve a mezclar cuando se terminan las tarjetas.
 */
class MazoMezclante<out T>(private val tarjetas: List<T>) {
    private var mazo = Mazo(tarjetas)

    fun sacarTarjeta(): T {
        if (mazo.isEmpty()) {
            mazo = Mazo(tarjetas)
        }
        return mazo.sacarTarjeta()
    }
}
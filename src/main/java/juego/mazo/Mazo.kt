package juego.mazo

/**
 * Un mazo al que, una vez que se terminan las cartas, no se le pueden volver a
 * sacar.
 */
class Mazo<out T>(tarjetas: List<T>) {
    private val actual = tarjetas.shuffled().toMutableList()

    fun sacarTarjeta(): T {
        val tarjeta = actual.last()
        actual.removeAt(actual.size - 1)
        return tarjeta
    }

    fun isEmpty() = actual.isEmpty()
}
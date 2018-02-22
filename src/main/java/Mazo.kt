class Mazo<out T>(private val tarjetas: List<T>) {
    private var actual: List<T> = tarjetas.shuffled()

    fun sacarTarjeta(): T {
        if (actual.isEmpty()) {
            actual = tarjetas.shuffled()
        }
        val tarjeta = actual.last()
        actual.dropLast(1)
        return tarjeta
    }
}
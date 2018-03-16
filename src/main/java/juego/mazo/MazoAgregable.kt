package juego.mazo

/**
 * Mazo al que se le tienen que volver a agregar tarjetas una vez que se sacan.
 *
 * Cuando se acaban las tarjetas del mazo, se vuelve a mezclar solo con las que
 * se agregaron nuevamente.
 */
class MazoAgregable<T>(tarjetas: List<T>) {
    private var mazo = Mazo(tarjetas)
    private val agregadas: MutableList<T> = mutableListOf()

    fun sacarTarjeta(): T {
        if (mazo.isEmpty()) {
            mazo = Mazo(agregadas)
            agregadas.clear()
        }
        return mazo.sacarTarjeta()
    }

    fun agregarTarjeta(tarjeta: T) {
        agregadas += tarjeta
    }

    fun agregarTarjetas(tarjetas: Array<out T>) {
        agregadas.addAll(tarjetas)
    }
}
package objetivos

import Color
import Jugador

class ObjetivoDeDestruirColor(val color: Color) : ObjetivoDeDestruir() {
    private val colorEnDescripcion = color.toString().toLowerCase().capitalize()

    override val descripcion: String =
            "Destruir al ejército $colorEnDescripcion; de ser imposible," +
                    "al jugador de la derecha."

    override fun jugadorADestruir(jugadores: List<Jugador>,
                                  jugadorActual: Int): Int {
        var indiceEnemigo = jugadores.indexOfFirst { it.color == color }
        if (indiceEnemigo == -1) {
            // si no hay enemigo con ese color,
            // agarramos el que esta a la derecha
            indiceEnemigo = (jugadorActual - 1) % jugadores.size
        }
        return indiceEnemigo
    }
}
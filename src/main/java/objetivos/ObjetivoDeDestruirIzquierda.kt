package objetivos

import juego.Jugador

class ObjetivoDeDestruirIzquierda : ObjetivoDeDestruir() {
    override val descripcion: String = "Destruir al jugador de la izquierda."

    override fun jugadorADestruir(jugadores: List<Jugador>,
                                  jugadorActual: Int): Int =
            //Devolvemos el jugador de la izquierda
            (jugadorActual + 1) % jugadores.size
}
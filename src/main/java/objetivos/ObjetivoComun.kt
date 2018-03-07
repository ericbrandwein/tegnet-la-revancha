package objetivos

import juego.Jugador
import paises.PaisEnJuego
import paises.cantPaisesConDueno

class ObjetivoComun : ObjetivoDeOcupar {
    override val descripcion = "Ocupar 45 países."

    override fun cumplido(paises: List<PaisEnJuego>, conquistado: PaisEnJuego,
                          jugadores: List<Jugador>,
                          jugadorActual: Int): Boolean =
            cantPaisesConDueno(paises, jugadorActual) + 1 >= 45
}
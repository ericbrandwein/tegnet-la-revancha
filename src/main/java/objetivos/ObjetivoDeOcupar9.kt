package objetivos

import juego.Jugador
import paises.Continente
import paises.PaisEnJuego

class ObjetivoDeOcupar9 : ObjetivoDeOcupar {
    override val descripcion =
            "Ocupar Oceanía, Africa, 4 países de América Central y 4 de Asia."

    override fun cumplido(paises: List<PaisEnJuego>, conquistado: PaisEnJuego,
            jugadores: List<Jugador>, jugadorActual: Int): Boolean {
        val oceaniaConquistado = paises.continenteConquistado(
                paises, Continente.OCEANIA, jugadorActual, conquistado)
        val africaConquistado = paises.continenteConquistado(
                paises, Continente.AFRICA, jugadorActual, conquistado)
        val cantAmericaCentralConquistado =
                paises.cantPaisesDeContinenteConDueno(
                        paises, Continente.AMERICA_CENTRAL, jugadorActual,
                        conquistado)
        val cantAsiaConquistado = paises.cantPaisesDeContinenteConDueno(
                paises, Continente.ASIA, jugadorActual, conquistado)
        return oceaniaConquistado && africaConquistado &&
                cantAmericaCentralConquistado >= 4 && cantAsiaConquistado >= 4
    }
}
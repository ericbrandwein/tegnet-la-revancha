package objetivos

import juego.Jugador
import paises.Continente
import paises.PaisEnJuego

class ObjetivoDeOcupar7 : ObjetivoDeOcupar {
    override val descripcion =
            "Ocupar América Central, 6 países de América del Sur, " +
                    "6 de Europa y 6 de Asia."

    override fun cumplido(paises: List<PaisEnJuego>, conquistado: PaisEnJuego,
            jugadores: List<Jugador>, jugadorActual: Int): Boolean {
        val americaCentralConquistado = paises.continenteConquistado(
                paises, Continente.AMERICA_CENTRAL, jugadorActual, conquistado)
        val cantAmericaDelSurConquistado =
                paises.cantPaisesDeContinenteConDueno(
                        paises, Continente.AMERICA_DEL_SUR, jugadorActual,
                        conquistado)
        val cantEuropaConquistado = paises.cantPaisesDeContinenteConDueno(
                paises, Continente.EUROPA, jugadorActual, conquistado)
        val cantAsiaConquistado = paises.cantPaisesDeContinenteConDueno(
                paises, Continente.ASIA, jugadorActual, conquistado)
        return americaCentralConquistado && cantAmericaDelSurConquistado >= 6 &&
                cantEuropaConquistado >= 6 && cantAsiaConquistado >= 6
    }
}
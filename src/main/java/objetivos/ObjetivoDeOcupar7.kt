package objetivos

import Jugador
import paises.Continente
import paises.PaisEnJuego

class ObjetivoDeOcupar7 : ObjetivoDeOcupar {
    override val descripcion =
            "Ocupar América Central, 6 países de América del Sur, " +
                    "6 de Europa y 6 de Asia."

    override fun cumplido(paises: List<PaisEnJuego>, conquistado: PaisEnJuego,
            jugadores: List<Jugador>, jugadorActual: Int): Boolean {
        val americaCentralConquistado = continenteConquistado(
                paises, Continente.AMERICA_CENTRAL, jugadorActual, conquistado)
        val cantAmericaDelSurConquistado = cantPaisesDeContinenteConDueno(
                paises, Continente.AMERICA_DEL_SUR, jugadorActual, conquistado)
        val cantEuropaConquistado = cantPaisesDeContinenteConDueno(
                paises, Continente.EUROPA, jugadorActual, conquistado)
        val cantAsiaConquistado = cantPaisesDeContinenteConDueno(
                paises, Continente.ASIA, jugadorActual, conquistado)
        return americaCentralConquistado && cantAmericaDelSurConquistado >= 6 &&
                cantEuropaConquistado >= 6 && cantAsiaConquistado >= 6
    }
}
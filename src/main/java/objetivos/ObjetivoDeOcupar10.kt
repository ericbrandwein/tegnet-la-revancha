package objetivos

import Jugador
import paises.Continente
import paises.PaisEnJuego

class ObjetivoDeOcupar10 : ObjetivoDeOcupar {
    override val descripcion =
            "Ocupar Europa, 4 países de Asia, 4 países de América del Sur."

    override fun cumplido(paises: List<PaisEnJuego>, conquistado: PaisEnJuego,
            jugadores: List<Jugador>, jugadorActual: Int): Boolean {
        val europaConquistado = continenteConquistado(
                paises, Continente.EUROPA, jugadorActual, conquistado)
        val cantAsiaConquistado = cantPaisesDeContinenteConDueno(
                paises, Continente.ASIA, jugadorActual, conquistado)
        val cantAmericaDelSurConquistado = cantPaisesDeContinenteConDueno(
                paises, Continente.AMERICA_DEL_SUR, jugadorActual, conquistado)
        return europaConquistado && cantAsiaConquistado >= 4 &&
                cantAmericaDelSurConquistado >= 4
    }
}
package objetivos

import juego.Jugador
import paises.Continente
import paises.PaisEnJuego
import paises.cantPaisesDeContinenteConDuenoMasConquistado
import paises.continenteConquistado

class ObjetivoDeOcupar10 : ObjetivoDeOcupar {
    override val descripcion =
            "Ocupar Europa, 4 países de Asia, 4 países de América del Sur."

    override fun cumplido(paises: List<PaisEnJuego>, conquistado: PaisEnJuego,
            jugadores: List<Jugador>, jugadorActual: Int): Boolean {
        val europaConquistado = continenteConquistado(
                paises, Continente.EUROPA, jugadorActual, conquistado)
        val cantAsiaConquistado = cantPaisesDeContinenteConDuenoMasConquistado(
                paises, Continente.ASIA, jugadorActual, conquistado)
        val cantAmericaDelSurConquistado =
                cantPaisesDeContinenteConDuenoMasConquistado(
                        paises, Continente.AMERICA_DEL_SUR, jugadorActual,
                        conquistado)
        return europaConquistado && cantAsiaConquistado >= 4 &&
                cantAmericaDelSurConquistado >= 4
    }
}
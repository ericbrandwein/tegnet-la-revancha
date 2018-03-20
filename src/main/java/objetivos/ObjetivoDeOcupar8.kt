package objetivos

import juego.Jugador
import paises.Continente
import paises.PaisEnJuego
import paises.cantPaisesDeContinenteConDuenoMasConquistado
import paises.continenteConquistado

class ObjetivoDeOcupar8 : ObjetivoDeOcupar {
    override val descripcion =
            "Ocupar América del Sur, Africa y 8 países de Asia."

    override fun cumplido(paises: List<PaisEnJuego>, conquistado: PaisEnJuego,
            jugadores: List<Jugador>, jugadorActual: Int): Boolean {
        val americaDelSurConquistado = continenteConquistado(
                paises, Continente.AMERICA_DEL_SUR, jugadorActual, conquistado)
        val africaConquistado = continenteConquistado(
                paises, Continente.AFRICA, jugadorActual, conquistado)
        val cantAsiaConquistado = cantPaisesDeContinenteConDuenoMasConquistado(
                paises, Continente.ASIA, jugadorActual, conquistado)
        return americaDelSurConquistado && africaConquistado
                && cantAsiaConquistado >= 8
    }
}
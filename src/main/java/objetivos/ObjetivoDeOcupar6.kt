package objetivos

import juego.Jugador
import paises.Continente
import paises.PaisEnJuego

class ObjetivoDeOcupar6 : ObjetivoDeOcupar {
    override val descripcion = "Ocupar Oceanía, 6 países de Asia, " +
            "6 de Africa y 6 de América del Norte."

    override fun cumplido(paises: List<PaisEnJuego>, conquistado: PaisEnJuego,
            jugadores: List<Jugador>, jugadorActual: Int): Boolean {
        val oceaniaConquistado = paises.continenteConquistado(
                paises, Continente.OCEANIA, jugadorActual, conquistado)
        val cantDeAfricaConquistados = paises.cantPaisesDeContinenteConDueno(
                paises, Continente.AFRICA, jugadorActual, conquistado)
        val cantDeAmericaDelNorteConquistados =
                paises.cantPaisesDeContinenteConDueno(
                        paises, Continente.AMERICA_DEL_NORTE, jugadorActual,
                        conquistado)
        return oceaniaConquistado && cantDeAfricaConquistados >= 6
                && cantDeAmericaDelNorteConquistados >= 6
    }
}
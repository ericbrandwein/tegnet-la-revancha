package objetivos

import juego.Jugador
import paises.Continente
import paises.PaisEnJuego

class ObjetivoDeOcupar2 : ObjetivoDeOcupar {
    override val descripcion =
            "Ocupar América del Norte, Oceanía y 5 países de Africa."

    override fun cumplido(paises: List<PaisEnJuego>, conquistado: PaisEnJuego,
            jugadores: List<Jugador>, jugadorActual: Int): Boolean {
        val americaDelNorteOcupado = paises.continenteConquistado(
                paises, Continente.AMERICA_DEL_NORTE, jugadorActual,
                conquistado)
        val oceaniaConquistado = paises.continenteConquistado(
                paises, Continente.OCEANIA, jugadorActual, conquistado)
        val cantOcupadosDeAfrica = paises.cantPaisesDeContinenteConDueno(
                paises, Continente.AFRICA, jugadorActual, conquistado)
        return americaDelNorteOcupado && oceaniaConquistado &&
                cantOcupadosDeAfrica >= 5
    }
}
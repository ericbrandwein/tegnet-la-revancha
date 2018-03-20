package objetivos

import juego.Jugador
import paises.Continente
import paises.PaisEnJuego
import paises.cantPaisesDeContinenteConDuenoMasConquistado
import paises.continenteConquistado

class ObjetivoDeOcupar4 : ObjetivoDeOcupar {
    override val descripcion =
            "Ocupar América del Norte, 8 países de Asia y 4 de Europa"

    override fun cumplido(paises: List<PaisEnJuego>, conquistado: PaisEnJuego,
            jugadores: List<Jugador>, jugadorActual: Int): Boolean {
        val americaDelNorteConquistado = continenteConquistado(
                paises, Continente.AMERICA_DEL_NORTE, jugadorActual,
                conquistado)
        val cantPaisesDeAsiaConquistados =
                cantPaisesDeContinenteConDuenoMasConquistado(
                        paises, Continente.ASIA, jugadorActual, conquistado)
        val cantPaisesDeEuropaConquistados =
                cantPaisesDeContinenteConDuenoMasConquistado(
                        paises, Continente.EUROPA, jugadorActual, conquistado)
        return americaDelNorteConquistado &&
                cantPaisesDeAsiaConquistados >= 8 &&
                cantPaisesDeEuropaConquistados >= 4
    }
}
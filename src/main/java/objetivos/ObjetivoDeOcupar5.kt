package objetivos

import juego.Jugador
import paises.Continente
import paises.PaisEnJuego
import paises.cantPaisesDeContinenteConDuenoMasConquistado

class ObjetivoDeOcupar5 : ObjetivoDeOcupar {
    override val descripcion =
            "Ocupar 4 países de América del Norte, 4 de Europa, 4 de Asia, " +
                    "3 de América del Sur, 3 de América Central, " +
                    "3 de Africa y 3 de Oceanía."

    override fun cumplido(paises: List<PaisEnJuego>, conquistado: PaisEnJuego,
            jugadores: List<Jugador>, jugadorActual: Int): Boolean {
        val cantPaisesAmericaDelNorte = cantPaisesDeContinenteConDuenoMasConquistado(
                paises, Continente.AMERICA_DEL_NORTE, jugadorActual,
                conquistado)
        val cantPaisesEuropa = cantPaisesDeContinenteConDuenoMasConquistado(
                paises, Continente.EUROPA, jugadorActual, conquistado)
        val cantPaisesAsia = cantPaisesDeContinenteConDuenoMasConquistado(
                paises, Continente.ASIA, jugadorActual, conquistado)
        val cantPaisesAmericaDelSur = cantPaisesDeContinenteConDuenoMasConquistado(
                paises, Continente.AMERICA_DEL_SUR, jugadorActual, conquistado)
        val cantPaisesAmericaCentral = cantPaisesDeContinenteConDuenoMasConquistado(
                paises, Continente.AMERICA_CENTRAL, jugadorActual, conquistado)
        val cantPaisesAfrica = cantPaisesDeContinenteConDuenoMasConquistado(
                paises, Continente.AFRICA, jugadorActual, conquistado)
        val cantPaisesOceania = cantPaisesDeContinenteConDuenoMasConquistado(
                paises, Continente.OCEANIA, jugadorActual, conquistado)
        return cantPaisesAmericaDelNorte >= 4 &&
                cantPaisesEuropa >= 4 &&
                cantPaisesAsia >= 4 &&
                cantPaisesAmericaDelSur >= 3 &&
                cantPaisesAmericaCentral >= 3 &&
                cantPaisesAfrica >= 3 &&
                cantPaisesOceania >= 3
    }
}
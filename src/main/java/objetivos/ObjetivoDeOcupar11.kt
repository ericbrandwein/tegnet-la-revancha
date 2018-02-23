package objetivos

import juego.Jugador
import paises.Continente
import paises.PaisEnJuego

class ObjetivoDeOcupar11 : ObjetivoDeOcupar {
    override val descripcion =
            "Ocupar Africa, 4 países de Europa, 4 países de Asia y 6 islas, " +
                    "repartidas en por lo menos 3 continentes."

    val nombresIslas: List<String> = listOf(
            "Isla Victoria", "Groenlandia", "Labrador", "Islandia", "Irlanda",
            "Gran Bretaña", "Cuba", "Jamaica", "Madagascar", "Japon", "Sumatra",
            "Filipinas", "Tonga", "Tasmania", "Nueva Zelandia"
    )

    override fun cumplido(paises: List<PaisEnJuego>, conquistado: PaisEnJuego,
            jugadores: List<Jugador>, jugadorActual: Int): Boolean {
        val africaConquistado = continenteConquistado(
                paises, Continente.AFRICA, jugadorActual, conquistado)
        val cantEuropaConquistados = cantPaisesDeContinenteConDueno(
                paises, Continente.EUROPA, jugadorActual, conquistado)
        val cantAsiaConquistados = cantPaisesDeContinenteConDueno(
                paises, Continente.ASIA, jugadorActual, conquistado)
        // paises que pueden contar entre las islas que se necesitan
        val sobranDeEuropa = cantEuropaConquistados - 4
        val sobranDeAsia = cantAsiaConquistados - 4
        val islasDeJugadorAprobadas = islasAprobadas(
                paises, jugadorActual, sobranDeEuropa, sobranDeAsia)
        val sonDeTresContinentes =
                sonDeTresContinentesDiferentes(islasDeJugadorAprobadas)
        return africaConquistado && cantEuropaConquistados >= 4 &&
                cantAsiaConquistados >= 4 &&
                islasDeJugadorAprobadas.size >= 6 &&
                sonDeTresContinentes
    }

    private fun sonDeTresContinentesDiferentes(
            islas: List<PaisEnJuego>): Boolean =
            islas.distinctBy { it.pais.continente }.size >= 3


    private fun islasAprobadas(paises: List<PaisEnJuego>,
            jugadorActual: Int, sobranDeEuropa: Int,
            sobranDeAsia: Int): List<PaisEnJuego> {
        var maxIslasAprobadasEnEuropa = sobranDeEuropa
        var maxIslasAprobadasEnAsia = sobranDeAsia
        return paises.filter { nombresIslas.contains(it.pais.nombre) }
                .filter { it.dueno == jugadorActual }
                .filter {
                    // la isla no puede ser de Africa,
                    // porque conquistar todo Africa es parte del objetivo
                    it.pais.continente != Continente.AFRICA
                }
                .filter {
                    // contamos solo las islas que no estan incluidas
                    // dentro de los 4 paises de europa que necesitamos
                    var aprobado = true
                    if (it.pais.continente == Continente.EUROPA) {
                        if (maxIslasAprobadasEnEuropa < 1) {
                            aprobado = false
                        } else {
                            maxIslasAprobadasEnEuropa--
                        }
                    }
                    aprobado
                }
                .filter {
                    // contamos solo las islas que no estan incluidas
                    // dentro de los 4 paises de asia que necesitamos
                    var aprobado = true
                    if (it.pais.continente == Continente.ASIA) {
                        if (maxIslasAprobadasEnAsia < 1) {
                            aprobado = false
                        } else {
                            maxIslasAprobadasEnAsia--
                        }
                    }
                    aprobado
                }
    }
}
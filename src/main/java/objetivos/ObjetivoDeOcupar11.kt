package objetivos

import juego.Jugador
import paises.Continente
import paises.PaisEnJuego
import paises.cantPaisesDeContinenteConDueno

class ObjetivoDeOcupar11 : ObjetivoDeOcupar() {
    override val descripcion =
            "Ocupar Africa, 4 países de Europa, 4 países de Asia y 6 islas, " +
                    "repartidas en por lo menos 3 continentes."

    val nombresIslas: List<String> = listOf(
            "Isla Victoria", "Groenlandia", "Labrador", "Islandia", "Irlanda",
            "Gran Bretaña", "Cuba", "Jamaica", "Madagascar", "Japon", "Sumatra",
            "Filipinas", "Tonga", "Tasmania", "Nueva Zelandia"
    )

    private val PAISES_A_OCUPAR_EUROPA = 4
    private val PAISES_A_OCUPAR_ASIA = 4

    init {
        paisesDeContinentesAOcupar = mapOf(
                Continente.AFRICA to TODOS_LOS_PAISES,
                Continente.EUROPA to PAISES_A_OCUPAR_EUROPA,
                Continente.ASIA to PAISES_A_OCUPAR_ASIA
        )
    }

    override fun cumplido(paises: Iterable<PaisEnJuego>,
            jugadores: List<Jugador>, jugadorActual: Int,
            jugadorAtacado: Int): Boolean {
        val ocupadosPaisesDeContinentes =
                super.cumplido(paises, jugadores, jugadorActual, jugadorAtacado)

        val cantEuropaConquistados = cantPaisesDeContinenteConDueno(
                paises, Continente.EUROPA, jugadorActual)
        val cantAsiaConquistados = cantPaisesDeContinenteConDueno(
                paises, Continente.ASIA, jugadorActual)
        // paises que pueden contar entre las islas que se necesitan
        val sobranDeEuropa = cantEuropaConquistados - PAISES_A_OCUPAR_EUROPA
        val sobranDeAsia = cantAsiaConquistados - PAISES_A_OCUPAR_ASIA
        val islasDeJugadorAprobadas = islasAprobadas(
                paises, jugadorActual, sobranDeEuropa, sobranDeAsia)
        val sonDeTresContinentes =
                sonDeTresContinentesDiferentes(islasDeJugadorAprobadas)
        return ocupadosPaisesDeContinentes &&
                islasDeJugadorAprobadas.size >= 6 &&
                sonDeTresContinentes
    }

    private fun sonDeTresContinentesDiferentes(
            islas: List<PaisEnJuego>): Boolean =
            islas.distinctBy { it.pais.continente }.size >= 3

    /**
     * Devuelve las islas que son del jugador que son elegibles para ser parte
     * de las islas que pide el objetivo.
     */
    private fun islasAprobadas(paises: Iterable<PaisEnJuego>,
            jugadorActual: Int, sobranDeEuropa: Int,
            sobranDeAsia: Int): List<PaisEnJuego> {
        val islasDeJugador =
                paises.filter { nombresIslas.contains(it.pais.nombre) }
                        .filter { it.dueno == jugadorActual }
                        .filter {
                            // la isla no puede ser de Africa,
                            // porque conquistar todo Africa es parte del objetivo
                            it.pais.continente != Continente.AFRICA
                        }
        // Solo aprobamos hasta una cantidad maxima de islas de europa
        // y de asia, porque las otras las estamos contando en la otra parte
        // del objetivo.
        val islasSinSobrantesDeEuropa = sacarPaisesSobrantesDeContinente(
                islasDeJugador, Continente.EUROPA, sobranDeEuropa)
        return sacarPaisesSobrantesDeContinente(
                islasSinSobrantesDeEuropa, Continente.ASIA, sobranDeAsia)
    }

    /**
     * Saca elementos de un iterable del [continente] para que no haya más del [maximo].
     */
    fun sacarPaisesSobrantesDeContinente(paises: Iterable<PaisEnJuego>,
            continente: Continente, maximo: Int): List<PaisEnJuego> {
        var cantidad = maximo
        return paises.filter {
            var aprobado = true
            if (it.pais.continente == continente) {
                if (cantidad < 1) {
                    aprobado = false
                } else {
                    cantidad--
                }
            }
            aprobado
        }
    }
}
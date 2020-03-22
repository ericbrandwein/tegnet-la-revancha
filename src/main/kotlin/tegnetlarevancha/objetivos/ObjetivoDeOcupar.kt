package objetivos

import juego.Jugador
import paises.Continente
import paises.PaisEnJuego
import paises.cantPaisesConDueno
import paises.continenteOcupado

abstract class ObjetivoDeOcupar : Objetivo {
    protected val TODOS_LOS_PAISES = -1

    /**
     * Mapa de cantidad de paises necesarios en qué continentes para que
     * se cumpla el objetivo. Si para algún continente, el número es
     * [TODOS_LOS_PAISES], se asume que se debe conquistar todo el continente.
     */
    protected var paisesDeContinentesAOcupar: Map<Continente, Int> = mapOf()

    override fun puedeCumplirse(paises: Iterable<PaisEnJuego>,
            jugadores: List<Jugador>, jugadorActual: Int) = true

    override fun cumplido(paises: Iterable<PaisEnJuego>,
            jugadores: List<Jugador>, jugadorActual: Int,
            jugadorAtacado: Int) =
            paisesDeContinentesAOcupar.all { (continente, cantPaises) ->
                if (cantPaises == TODOS_LOS_PAISES)
                    continenteOcupado(paises, continente, jugadorActual)
                else cantPaisesConDueno(paises, jugadorActual) >= cantPaises
            }
}
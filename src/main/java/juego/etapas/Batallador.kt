package juego.etapas

import juego.getRandomInt
import paises.PaisEnJuego
import kotlin.math.min

/**
 * Simula una batalla entre los paises, y les saca la
 * cantidad de ejercitos que correspondan.
 */
fun batallar(desde: PaisEnJuego, hacia: PaisEnJuego) {
    val cantDadosAtacante = cantDadosAtacante(desde)
    val cantDadosDefensor = cantDadosDefensor(hacia)
    var tiradaAtacante = tirarDados(cantDadosAtacante)
    var tiradaDefensor = tirarDados(cantDadosDefensor)

    // hago que tengan la misma cantidad de elementos, ya que ya estan ordenados
    val ejercitosEnRiesgo = min(cantDadosAtacante, cantDadosDefensor)
    tiradaAtacante = tiradaAtacante.take(ejercitosEnRiesgo)
    tiradaDefensor = tiradaDefensor.take(ejercitosEnRiesgo)

    val ganados = cantGanados(tiradaAtacante, tiradaDefensor)
    desde.ejercitos -= ejercitosEnRiesgo - ganados
    hacia.ejercitos -= ganados
}

/**
 * Devuelve la cantidad de ejercitos ganados por parte del atacante.
 * Toma las listas de dados tirados por cada uno, que deben tener el mismo size.
 */
private fun cantGanados(tiradaAtacante: List<Int>, tiradaDefensor: List<Int>) =
        tiradaAtacante
                .mapIndexed { indice, dado -> dado > tiradaDefensor[indice] }
                .count { it }

private fun cantDadosAtacante(atacante: PaisEnJuego) =
        min(atacante.ejercitos - 1, 3)

private fun cantDadosDefensor(defensor: PaisEnJuego) =
        min(defensor.ejercitos, 3)

/**
 * Devuelve una lista de cant dados tirados, en orden de mayor a menor
 */
private fun tirarDados(cant: Int): List<Int> {
    val dados = ArrayList<Int>()
    repeat(cant) {
        dados.add(tirarDado())
    }
    return dados.sortedDescending()
}

private fun tirarDado() = getRandomInt(6) + 1

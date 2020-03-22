package juego.faseprincipal.etapas.ataque

import juego.faseprincipal.situacion.TarjetaDeSituacion
import juego.getRandomInt
import paises.PaisEnJuego
import kotlin.math.min

/**
 * Simula una batalla entre los paises, y les saca la
 * cantidad de ejercitos que correspondan.
 */
fun batallar(desde: PaisEnJuego, hacia: PaisEnJuego,
        situacion: TarjetaDeSituacion) {
    val cantDadosAtacante =
            cantDadosAtacante(desde.ejercitos, hacia.ejercitos, situacion)
    val cantDadosDefensor = cantDadosDefensor(hacia.ejercitos, situacion)
    var tiradaAtacante = tirarDados(cantDadosAtacante)
    var tiradaDefensor = tirarDados(cantDadosDefensor)

    // hago que tengan la misma cantidad de elementos, ya que ya estan ordenados
    val ejercitosEnRiesgo = min(min(desde.ejercitos - 1, hacia.ejercitos), 3)
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

private fun cantDadosAtacante(ejercitosAtacante: Int, ejercitosDefensor: Int,
        situacion: TarjetaDeSituacion): Int {
    var dados = min(ejercitosAtacante - 1, 3)
    if (situacion == TarjetaDeSituacion.VIENTO_A_FAVOR) {
        dados++
    }
    if (ejercitosDefensor >= 3 &&
            ejercitosAtacante >= ejercitosDefensor * 2) {
        dados = 4
    }
    return dados
}

private fun cantDadosDefensor(ejercitosDefensor: Int,
        situacion: TarjetaDeSituacion): Int {
    var dados = min(ejercitosDefensor, 3)
    if (situacion == TarjetaDeSituacion.NIEVE) {
        dados++
    }
    return dados
}

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

package paises

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File

private const val NOMBRE_ARCHIVO_PAISES = "/paises.json"

// Esto es feo y lo se, no se me ocurre como hacerlo mas lindo, y queria usar json

fun listaDePaises(): List<Pais> {
    val archivoPaises = File(
            Object::class.java.getResource(NOMBRE_ARCHIVO_PAISES).toURI())
    val mapper = ObjectMapper()
    val mapa = mapper.readValue(archivoPaises, Map::class.java)

    return mapaAPaises(mapa)
}

private fun mapaAPaises(mapa: Map<*, *>): List<Pais> {
    val listaDePaises = mutableListOf<Pais>()
    for ((continente, paises) in mapa.entries) {
        paises as Map<*, *>
        for ((nombre, conecta) in paises) {
            val conectaCon = (conecta as Map<*, *>)["conecta"] as List<String>
            nombre as String
            val continenteDeEnum = Continente.valueOf(continente as String)
            val nuevoPais = Pais(nombre, continenteDeEnum, conectaCon)
            listaDePaises.add(nuevoPais)
        }
    }
    return listaDePaises
}
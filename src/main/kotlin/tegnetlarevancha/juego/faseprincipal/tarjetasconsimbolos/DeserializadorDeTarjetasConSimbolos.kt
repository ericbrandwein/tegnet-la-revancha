package juego.faseprincipal.tarjetasconsimbolos

import com.fasterxml.jackson.databind.ObjectMapper
import paises.Continente
import java.io.File
import java.util.*

private const val ARCHIVO_DE_TARJETAS_DE_PAIS = "/tarjetas-paises.json"
private const val ARCHIVO_DE_TARJETAS_DE_CONTINENTE =
        "/tarjetas-continentes.json"

fun deserializarTarjetasDePaises(): List<TarjetaDePais> {
    val tarjetasJson = listaDesdeArchivo(ARCHIVO_DE_TARJETAS_DE_PAIS)

    val tarjetas = mutableListOf<TarjetaDePais>()

    for (tarjeta in tarjetasJson) {
        tarjeta as Map<String, *>
        val nombre = tarjeta["nombre"] as String
        val simbolos = convertirAEnumSet(tarjeta["simbolos"] as List<String>)
        tarjetas += TarjetaDePais(nombre, simbolos)
    }

    return tarjetas
}

fun deserializarTarjetasDeContinentes(): List<TarjetaDeContinente> {
    val tarjetasJson = listaDesdeArchivo(ARCHIVO_DE_TARJETAS_DE_CONTINENTE)

    val tarjetas = mutableListOf<TarjetaDeContinente>()

    for (tarjeta in tarjetasJson) {
        tarjeta as Map<String, *>
        val nombre = Continente.valueOf(tarjeta["nombre"] as String)
        val simbolos = convertirAEnumSet(tarjeta["simbolos"] as List<String>)
        tarjetas += TarjetaDeContinente(nombre, simbolos)
    }

    return tarjetas
}

private fun listaDesdeArchivo(nombreArchivo: String): List<*> {
    val mapper = ObjectMapper()
    val archivo = File(
            Object::class.java.getResource(nombreArchivo).toURI()
    )
    return mapper.readValue(archivo, List::class.java)
}

private fun convertirAEnumSet(strings: List<String>): EnumSet<Simbolo> {
    val valores = mutableListOf<Simbolo>()
    for (string in strings) {
        valores.add(Simbolo.valueOf(string))
    }
    return EnumSet.copyOf(valores)
}

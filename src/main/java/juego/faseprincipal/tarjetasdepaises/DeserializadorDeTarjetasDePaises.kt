package juego.faseprincipal.tarjetasdepaises

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File
import java.util.*

private const val ARCHIVO_DE_TARJETAS = "/tarjetas-paises.json"

fun deserializarTarjetasDePaises(): List<TarjetaDePais> {
    val mapper = ObjectMapper()
    val archivo = File(
            Object::class.java.getResource(ARCHIVO_DE_TARJETAS).toURI()
    )
    val tarjetasJson = mapper.readValue(archivo, List::class.java)

    val tarjetas = mutableListOf<TarjetaDePais>()

    for (tarjeta in tarjetasJson) {
        tarjeta as Map<String, *>
        val nombre = tarjeta["nombre"] as String
        val simbolos = convertirAEnumSet(tarjeta["simbolos"] as List<String>)
        tarjetas += TarjetaDePais(nombre, simbolos)
    }

    return tarjetas
}

private fun convertirAEnumSet(strings: List<String>): EnumSet<Simbolo> {
    val valores = mutableListOf<Simbolo>()
    for (string in strings) {
        valores.add(Simbolo.valueOf(string))
    }
    return EnumSet.copyOf(valores)
}

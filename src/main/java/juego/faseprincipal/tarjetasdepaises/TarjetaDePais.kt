package juego.faseprincipal.tarjetasdepaises

import java.util.*

data class TarjetaDePais(val nombre: String, val simbolos: EnumSet<Simbolo>)

enum class Simbolo {
    AVION,
    SOLDADO,
    ANCLA,
    COMODIN
}
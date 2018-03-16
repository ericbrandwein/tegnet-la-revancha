package juego.faseprincipal.tarjetasconsimbolos

import paises.Continente
import java.util.*

open class TarjetaConSimbolos(val simbolos: EnumSet<Simbolo>)

class TarjetaDePais(val nombre: String, simbolos: EnumSet<Simbolo>) :
        TarjetaConSimbolos(simbolos)

class TarjetaDeContinente(val continente: Continente, simbolos: EnumSet<Simbolo>) :
        TarjetaConSimbolos(simbolos)

enum class Simbolo {
    AVION,
    SOLDADO,
    ANCLA,
    COMODIN
}
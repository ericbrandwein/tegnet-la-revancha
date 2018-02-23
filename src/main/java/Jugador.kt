import objetivos.Objetivo

class Jugador(var nombre: String, val color: Color, var objetivo: Objetivo? = null) {
    var perdio = false
}
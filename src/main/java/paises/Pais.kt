package paises

class Pais(val nombre: String, val continente: Continente,
           var dueno: Int = 0) {
    var ejercitos = 1
    var misiles = 0

    fun equals(otro: Pais) =
            otro.nombre == nombre && otro.continente == continente
}
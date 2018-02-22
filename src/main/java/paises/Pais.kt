package paises

class Pais(val nombre: String, val continente: Continente,
           val conectaCon: List<String>) {

    fun equals(otro: Pais) =
            otro.nombre == nombre && otro.continente == continente

    override fun toString() = "$nombre de $continente"
}
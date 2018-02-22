import paises.Pais

class Juego (val jugadores: List<Jugador>) {
    var mano: Int = 0
    var tarjetaDeSituacion: TarjetaDeSituacion =
            TarjetaDeSituacion.COMBATE_CLASICO
    var paises : List<Pais> = listaDePaises()
    private val mazoDeSituacion: Mazo<TarjetaDeSituacion> =
            armarMazoDeSituacion()


    fun elegirMano() {
        mano = (Math.random() * jugadores.size).toInt()
    }

    fun repartirPaises() {
        
    }

    fun sacarTarjetaDeSituacion() {
        tarjetaDeSituacion = mazoDeSituacion.sacarTarjeta()
    }
}
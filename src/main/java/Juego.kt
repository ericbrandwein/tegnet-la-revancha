class Juego (val jugadores: List<Jugador>) {
    var mano: Int = 0
    var tarjetaDeSituacion: TarjetaDeSituacion =
            TarjetaDeSituacion.COMBATE_CLASICO
    private var mazoDeSituacion: Mazo<TarjetaDeSituacion> =
            armarMazoDeSituacion()


    fun elegirMano() {

    }

    fun sacarTarjetaDeSituacion() {
        tarjetaDeSituacion = mazoDeSituacion.sacarTarjeta()
    }
}
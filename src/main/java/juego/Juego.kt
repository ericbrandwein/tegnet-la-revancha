package juego

import objetivos.Objetivo
import objetivos.listaDeObjetivos
import paises.PaisEnJuego
import paises.listaDePaises

class Juego(val jugadores: List<Jugador>) {
    var mano: Int = getRandomInt(jugadores.size)
    var paises: List<PaisEnJuego> = PaisEnJuego.desdePaises(listaDePaises())
    private val mazoDeSituacion: Mazo<TarjetaDeSituacion> =
            armarMazoDeSituacion()
    var tarjetaDeSituacion: TarjetaDeSituacion =
            mazoDeSituacion.sacarTarjeta()

    init {
        repartirPaises()
        repartirObjetivos()
    }

    fun repartirPaises() {
        paises = paises.shuffled()
        for (i in 0 until paises.size / jugadores.size) {
            for (j in 0 until jugadores.size) {
                paises[i + j].dueno = j
            }
        }
        if (jugadores.size == 5) {
            // sobran 2 paises
            val primerJugador = getRandomInt(jugadores.size)
            // como el primer jugador no puede ganar los dos paises,
            // hacemos random entre los otros 4 jugadores
            var segundoJugador = getRandomInt(jugadores.size - 1)
            if(segundoJugador >= primerJugador) segundoJugador++

            paises.last().dueno = segundoJugador
            paises[paises.size - 2].dueno = primerJugador
        }
    }

    fun repartirObjetivos() {
        var objetivos : List<Objetivo> = listaDeObjetivos().shuffled()
        jugadores.forEach {
            it.objetivo = objetivos.last()
            objetivos = objetivos.dropLast(1)
        }
    }

    fun sacarTarjetaDeSituacion(): TarjetaDeSituacion {
        tarjetaDeSituacion = mazoDeSituacion.sacarTarjeta()
        return tarjetaDeSituacion
    }
}
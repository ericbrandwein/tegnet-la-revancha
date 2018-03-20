package juego.faseprincipal.etapas

import juego.faseprincipal.GastadorDeTarjetasDePais
import juego.faseprincipal.canjes.Canjeador
import juego.faseprincipal.tarjetasconsimbolos.TarjetaConSimbolos
import juego.faseprincipal.tarjetasconsimbolos.TarjetaDeContinente
import juego.faseprincipal.tarjetasconsimbolos.TarjetaDePais
import juego.faseprincipal.tarjetasconsimbolos.TarjetasDeJugadores
import paises.PaisEnJuego
import paises.cantPaisesConDueno
import paises.paisConNombre
import kotlin.math.max

private const val MINIMO_DE_EJERCITOS_INCORPORABLES = 4

class EncargadoEtapaIncorporacion(private val jugador: Int,
        private val paises: List<PaisEnJuego>,
        private val tarjetasDeJugadores: TarjetasDeJugadores,
        private val canjeador: Canjeador,
        private val gastadorDeTarjetasDePais: GastadorDeTarjetasDePais) {
    var ejercitosRestantesAIncorporar = max(
            cantPaisesConDueno(paises, jugador) / 2,
            MINIMO_DE_EJERCITOS_INCORPORABLES)
        private set
    private var hizoCanje = false
    val tarjetasDePais: Set<TarjetaDePais>
        get() = tarjetasDeJugadores.tarjetasDePaisDe(jugador)
    val tarjetasDeContinente: Set<TarjetaDeContinente>
        get() = tarjetasDeJugadores.tarjetasDeContinenteDe(jugador)

    fun incorporar(nombrePais: String, ejercitos: Int) {
        val pais = paisConNombre(paises, nombrePais)
        pais.ejercitos += ejercitos
        ejercitosRestantesAIncorporar -= ejercitos
    }

    fun puedeHacerCanje() = !hizoCanje && canjeador.puedeHacerCanje(jugador)

    fun puedeHacerCanjeConTarjetas(tarjetas: Set<TarjetaConSimbolos>) =
            canjeador.puedeHacerCanjeConTarjetas(tarjetas)

    fun cantEjercitosParaProximoCanje() =
            canjeador.cantEjercitosParaCanje(canjeador.cantCanjes(jugador))

    /**
     * Canjea las tarjetas por ejercitos incorporables,
     * y agrega los ejercitos a [ejercitosRestantesAIncorporar].
     *
     * Debe no haber hecho todavía un canje,
     * se debe poder hacer un canje con las tarjetas,
     * y las tarjetas deben ser del jugador.
     *
     * @see puedeHacerCanje
     * @see puedeHacerCanjeConTarjetas
     * @see EncargadoEtapaIncorporacion.tarjetasDePais
     * @see EncargadoEtapaIncorporacion.tarjetasDeContinente
     */
    fun canjear(tarjetasDePais: Set<TarjetaDePais>,
            tarjetasDeContinente: Set<TarjetaDeContinente>) {
        corroborarTarjetasDeJugador(tarjetasDePais, tarjetasDeContinente)
        corroborarPuedeHacerCanjeConTarjetas(
                tarjetasDePais, tarjetasDeContinente)
        corroborarCanjeNoHecho()

        ejercitosRestantesAIncorporar +=
                canjeador.canjear(jugador, tarjetasDePais, tarjetasDeContinente)
        gastadorDeTarjetasDePais.marcarTarjetasComoNoGastadas(
                jugador, tarjetasDePais)
        hizoCanje = true
    }

    private fun corroborarTarjetasDeJugador(tarjetasDePais: Set<TarjetaDePais>,
            tarjetasDeContinente: Set<TarjetaDeContinente>) {
        if (!this.tarjetasDeContinente.containsAll(tarjetasDeContinente)
                || !this.tarjetasDePais.containsAll(tarjetasDePais)) {
            throw IllegalArgumentException(
                    "El jugador actual no posee estas tarjetas")
        }
    }

    private fun corroborarPuedeHacerCanjeConTarjetas(
            tarjetasDePais: Set<TarjetaDePais>,
            tarjetasDeContinente: Set<TarjetaDeContinente>) {
        if (!puedeHacerCanjeConTarjetas(
                        tarjetasDePais + tarjetasDeContinente)) {
            throw IllegalArgumentException(
                    "No se puede hacer un canje con estas tarjetas")
        }
    }

    private fun corroborarCanjeNoHecho() {
        if (hizoCanje) {
            throw IllegalStateException(
                    "Se puede hacer maximo un canje por ronda")
        }
    }
}
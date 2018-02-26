package juego

import juego.faseincorporacion.EncargadoPrimeraFaseDeIncorporacion
import juego.faseprincipal.EncargadoFasePrincipal
import juego.faseprincipal.VistaFasePrincipal
import juego.faseprincipal.etapas.Incorporador

interface VistaJuego {
    val vistaFasePrincipal: VistaFasePrincipal

    fun primeraFaseDeIncorporacion(
            encargado: EncargadoPrimeraFaseDeIncorporacion)

    fun nuevoTurnoDeVueltaDeIncorporacion(incorporador: Incorporador)
    fun fasePrincipal(encargado: EncargadoFasePrincipal)
}
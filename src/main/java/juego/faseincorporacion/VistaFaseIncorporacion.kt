package juego.faseincorporacion

import juego.faseprincipal.etapas.Incorporador

interface VistaFaseIncorporacion {
    var encargado: EncargadoFaseDeIncorporacion
    fun nuevoTurnoDeVueltaDeIncorporacion(incorporador: Incorporador)
}
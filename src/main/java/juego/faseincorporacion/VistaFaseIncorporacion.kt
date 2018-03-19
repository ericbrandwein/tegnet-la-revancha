package juego.faseincorporacion

interface VistaFaseIncorporacion {
    var encargado: EncargadoFaseDeIncorporacion
    fun nuevoTurnoDeVueltaDeIncorporacion(incorporador: Incorporador)
}
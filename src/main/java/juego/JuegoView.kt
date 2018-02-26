package juego

import juego.etapas.Atacador
import juego.etapas.Incorporador
import juego.etapas.Reagrupador

interface JuegoView {
    fun primeraFaseDeIncorporacion()
    fun etapaDeIncorporacion(incorporador: Incorporador)
    fun etapaDeAtaque(atacador: Atacador)
    fun etapaDeReagrupe(reagrupador: Reagrupador)
    fun nuevoTurnoDeVueltaDeIncorporacion(incorporador: Incorporador)
}
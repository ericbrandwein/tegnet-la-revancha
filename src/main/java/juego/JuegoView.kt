package juego

import juego.fases.Atacador
import juego.fases.Incorporador
import juego.fases.Reagrupador

interface JuegoView {
    fun primeraVueltaDeIncorporacion()
    fun faseDeIncorporacion(incorporador: Incorporador)
    fun faseDeAtaque(atacador: Atacador)
    fun faseDeReagrupe(reagrupador: Reagrupador)
}
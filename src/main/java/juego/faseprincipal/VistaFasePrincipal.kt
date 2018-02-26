package juego.faseprincipal

import juego.faseprincipal.etapas.Atacador
import juego.faseprincipal.etapas.Incorporador
import juego.faseprincipal.etapas.Reagrupador

interface VistaFasePrincipal {
    fun etapaDeIncorporacion(incorporador: Incorporador)
    fun etapaDeAtaque(atacador: Atacador)
    fun etapaDeReagrupe(reagrupador: Reagrupador)
}
package juego.faseprincipal

import juego.faseprincipal.etapas.ataque.Atacador
import juego.faseprincipal.etapas.Incorporador
import juego.faseprincipal.etapas.Reagrupador
import juego.faseprincipal.etapas.ataque.VistaEtapaAtaque

interface VistaFasePrincipal {
    var encargado: EncargadoFasePrincipal
    val vistaEtapaAtaque: VistaEtapaAtaque

    fun etapaDeIncorporacion(incorporador: Incorporador)
    fun etapaDeAtaque(atacador: Atacador)
    fun etapaDeReagrupe(reagrupador: Reagrupador)
}
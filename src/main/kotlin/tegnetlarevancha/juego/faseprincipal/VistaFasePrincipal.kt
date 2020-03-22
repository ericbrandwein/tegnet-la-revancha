package juego.faseprincipal

import juego.faseprincipal.etapas.EncargadoEtapaIncorporacion
import juego.faseprincipal.etapas.EncargadoEtapaReagrupe
import juego.faseprincipal.etapas.ataque.EncargadoEtapaAtaque
import juego.faseprincipal.etapas.ataque.VistaEtapaAtaque

interface VistaFasePrincipal {
    var encargadoFase: EncargadoFasePrincipal
    val vistaEtapaAtaque: VistaEtapaAtaque

    fun etapaDeIncorporacion(encargado: EncargadoEtapaIncorporacion)
    fun etapaDeAtaque(encargado: EncargadoEtapaAtaque)
    fun etapaDeReagrupe(encargado: EncargadoEtapaReagrupe)
}
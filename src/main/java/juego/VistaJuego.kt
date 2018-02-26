package juego

import juego.faseincorporacion.VistaFaseIncorporacion
import juego.faseprincipal.VistaFasePrincipal

interface VistaJuego {
    val vistaFaseIncorporacion: VistaFaseIncorporacion
    val vistaFasePrincipal: VistaFasePrincipal
}
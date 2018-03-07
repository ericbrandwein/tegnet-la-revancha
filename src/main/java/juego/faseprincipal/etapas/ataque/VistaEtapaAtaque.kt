package juego.faseprincipal.etapas.ataque

import paises.PaisEnJuego

interface VistaEtapaAtaque {
    /**
     * Es llamado cuando se conquista el pais [atacado].
     * Determina cuantos ejercitos se deberían pasar.
     *
     * @see puedenPasarseDesde
     */
    fun cuantosPasar(atacante: PaisEnJuego, atacado: PaisEnJuego): Int

    /**
     * Se llama cuando el [jugador] pierde.
     */
    fun perdio(jugador: Int)
}
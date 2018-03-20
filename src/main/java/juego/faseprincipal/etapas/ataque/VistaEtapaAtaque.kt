package juego.faseprincipal.etapas.ataque

interface VistaEtapaAtaque {
    /**
     * Es llamado cuando se conquista el pais [atacado].
     * Se debe llamar al [callback] cuando se eligen los ejercitos.
     *
     * @see puedenPasarseDesde
     */
    fun elegidorDeEjercitosQuePasar(callback: (Int) -> Unit)

    /**
     * Se llama cuando el [jugador] pierde.
     */
    fun perdio(jugador: Int)
}
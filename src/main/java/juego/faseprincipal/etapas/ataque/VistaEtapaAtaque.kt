package juego.faseprincipal.etapas.ataque

interface VistaEtapaAtaque {
    /**
     * Es llamado cuando se conquista el pais [atacado].
     * Se debe llamar a [EncargadoEtapaAtaque.pasarEjercitos] cuando se
     * eligen los ejercitos.
     *
     * @see puedenPasarseDesde
     */
    fun elegidorDeEjercitosQuePasar()

    /**
     * Se llama cuando el [jugador] pierde.
     */
    fun perdio(jugador: Int)
}
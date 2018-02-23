package juego

private val CANT_TARJETAS =
        mapOf(
                TarjetaDeSituacion.COMBATE_CLASICO to 20,
                TarjetaDeSituacion.NIEVE to 4,
                TarjetaDeSituacion.VIENTO_A_FAVOR to 4,
                TarjetaDeSituacion.CRISIS to 4,
                TarjetaDeSituacion.REFUERZOS_EXTRA to 4,
                TarjetaDeSituacion.FRONTERAS_ABIERTAS to 4,
                TarjetaDeSituacion.FRONTERAS_CERRADAS to 4,
                TarjetaDeSituacion.DESCANSO_AMARILLO to 1,
                TarjetaDeSituacion.DESCANSO_AZUL to 1,
                TarjetaDeSituacion.DESCANSO_BLANCO to 1,
                TarjetaDeSituacion.DESCANSO_NEGRO to 1,
                TarjetaDeSituacion.DESCANSO_ROJO to 1,
                TarjetaDeSituacion.DESCANSO_VERDE to 1
        )


fun armarMazoDeSituacion() : Mazo<TarjetaDeSituacion> {
    val tarjetas = mutableListOf<TarjetaDeSituacion>()
    for ((tarjeta, cantidad) in CANT_TARJETAS)
        for (i in 1..cantidad)
            tarjetas.add(tarjeta)

    return Mazo(tarjetas)
}
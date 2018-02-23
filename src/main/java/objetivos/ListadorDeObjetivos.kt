package objetivos

import juego.Color

fun listaDeObjetivos(): List<Objetivo> = listOf(
        ObjetivoDeOcupar1(),
        ObjetivoDeOcupar2(),
        ObjetivoDeOcupar3(),
        ObjetivoDeOcupar4(),
        ObjetivoDeOcupar5(),
        ObjetivoDeOcupar6(),
        ObjetivoDeOcupar7(),
        ObjetivoDeOcupar8(),
        ObjetivoDeOcupar9(),
        ObjetivoDeOcupar10(),
        ObjetivoDeOcupar11(),
        ObjetivoDeOcupar12(),
        ObjetivoDeDestruirColor(Color.BLANCO),
        ObjetivoDeDestruirColor(Color.NEGRO),
        ObjetivoDeDestruirColor(Color.ROJO),
        ObjetivoDeDestruirColor(Color.AZUL),
        ObjetivoDeDestruirColor(Color.AMARILLO),
        ObjetivoDeDestruirColor(Color.VERDE),
        ObjetivoDeDestruirIzquierda()
)
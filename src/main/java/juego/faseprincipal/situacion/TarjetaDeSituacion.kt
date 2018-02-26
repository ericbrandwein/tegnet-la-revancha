package juego.faseprincipal.situacion

enum class TarjetaDeSituacion(val descripcion: String) {
    COMBATE_CLASICO("Las acciones bélicas se desarrollan sin modificaciones"),
    NIEVE("Los jugadores atacados se defienden con un dado más del " +
            "que les correspondería de acuerdo con la cantidad de ejércitos " +
            "que posean."),
    VIENTO_A_FAVOR("Los jugadores atacantes tiran con un dado más " +
            "del que les correspondería de acuerdo con la cantidad de " +
            "ejércitos que posean"),
    CRISIS("Cada jugador debe tirar un dado. El que saque el " +
            "número más bajo, no podrá sacar tarjeta de países por esa " +
            "vuelta. En caso de empate en el número más bajo, ninguno de los " +
            "jugadores que haya empatado podrá sacar tarjeta durante la " +
            "vuelta, aunque conservará su derecho a incorporar ejércitos, " +
            "atacar países ajenos y reagrupar."),
    REFUERZOS_EXTRA("Todos los jugadores tendrán derecho a " +
            "incorporar una suma extra de ejércitos, igual a la mitad de " +
            "los pases ocupados."),
    FRONTERAS_ABIERTAS("Sólo podrán realizarse ataques desde un " +
            "continente hacia afuera."),
    FRONTERAS_CERRADAS("Sólo podrán realizarse ataques dentro de " +
            "las fronteras de cada continente."),
    DESCANSO_AMARILLO("El color Amarillo sólo podrá incorporar " +
            "ejércitos durante su turno, pero no podrá atacar ni reagrupar."),
    DESCANSO_AZUL("El color Azul sólo podrá incorporar " +
            "ejércitos durante su turno, pero no podrá atacar ni reagrupar."),
    DESCANSO_BLANCO("El color Blanco sólo podrá incorporar " +
            "ejércitos durante su turno, pero no podrá atacar ni reagrupar."),
    DESCANSO_NEGRO("El color Negro sólo podrá incorporar " +
            "ejércitos durante su turno, pero no podrá atacar ni reagrupar."),
    DESCANSO_ROJO("El color Rojo sólo podrá incorporar " +
            "ejércitos durante su turno, pero no podrá atacar ni reagrupar."),
    DESCANSO_VERDE("El color Verde sólo podrá incorporar " +
            "ejércitos durante su turno, pero no podrá atacar ni reagrupar."),
}
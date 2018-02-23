import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.VBox
import javafx.stage.Stage
import juego.Juego
import juego.Jugador


fun main(args: Array<String>) {
    Application.launch(MainApplication::class.java, *args)
}

class MainApplication : Application(),
        PantallaInicialController.PantallaInicialListener {

    var juego: Juego? = null

    override fun start(primaryStage: Stage) {
        val loader = FXMLLoader(javaClass.getResource("pantalla-inicial.fxml"))
        primaryStage.scene = Scene(loader.load())
        val controller = loader.getController<PantallaInicialController>()
        controller.listener = this
        primaryStage.show()
    }

    override fun comenzarJuego(jugadores: List<Jugador>) {
        juego = Juego(jugadores)
        jugadores.forEach { println(it) }
    }
}
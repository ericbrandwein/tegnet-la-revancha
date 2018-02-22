import javafx.application.Application
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.GridPane
import javafx.stage.Stage


fun main(args: Array<String>) {
    Application.launch(MainApplication::class.java, *args)
}

class MainApplication : Application() {

    val btnEjercitosArgentina = Button()
    val btnEjercitosChile = Button()
    val argentina = Pais("Argentina", Continente.AMERICA_DEL_SUR, 0)
    val chile = Pais("Chile", Continente.AMERICA_DEL_SUR, 1)

    override fun start(primaryStage: Stage) {
        primaryStage.title = "Hello World!"
        val btnArgentina = Button()
        val btnChile = Button()
        btnChile.text = "Chile: Atacar"
        btnChile.onAction = EventHandler {
            batallar(chile, argentina)
            actualizarEjercitos()
        }
        btnArgentina.text = "Argentina: Atacar"
        btnArgentina.onAction = EventHandler<ActionEvent> {
            batallar(argentina, chile)
            actualizarEjercitos()
        }
        btnEjercitosArgentina.text = "1"
        btnEjercitosArgentina.onAction = EventHandler {
            argentina.ejercitos++
            actualizarEjercitos()
        }
        btnEjercitosChile.text = "1"
        btnEjercitosChile.onAction = EventHandler {
            chile.ejercitos++
            actualizarEjercitos()
        }

        val grid = GridPane()
        grid.add(btnArgentina, 0, 0, 1, 1)
        grid.add(btnEjercitosArgentina, 1, 0, 1, 1)
        grid.add(btnChile, 0, 1, 1, 1)
        grid.add(btnEjercitosChile, 1, 1, 1, 1)

        primaryStage.scene = Scene(grid, 300.0, 250.0)
        primaryStage.show()
    }

    fun actualizarEjercitos() {
        btnEjercitosArgentina.text = argentina.ejercitos.toString()
        btnEjercitosChile.text = chile.ejercitos.toString()
    }
}

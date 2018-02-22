import javafx.application.Application
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.GridPane
import javafx.scene.layout.StackPane
import javafx.stage.Stage


fun main (args: Array<String>) {
    Application.launch(MainApplication::class.java, *args)
}

class MainApplication : Application() {

    override fun start(primaryStage: Stage) {
        primaryStage.title = "Hello World!"
        val btn = Button()
        btn.text = "Say 'Hello World'"
        btn.onAction = EventHandler<ActionEvent> { println("Hello World!") }

        val grid = GridPane()
        grid.add(btn, 0, 0, 1, 1)
        val btn2 = Button()
        btn2.text = "Say 'Goodbye'"
        btn2.onAction = EventHandler { println("hola") }
        grid.add(btn2, 1, 1, 1, 1)
        primaryStage.scene = Scene(grid, 300.0, 250.0)
        primaryStage.show()
    }
}

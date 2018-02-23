import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import juego.Color
import juego.Juego
import juego.Jugador
import java.net.URL
import java.util.*

class PantallaInicialController : Initializable {

    @FXML
    lateinit var fieldNombre: TextField
    @FXML
    lateinit var comboColor: ComboBox<Color>

    val jugadores: MutableList<Jugador> = mutableListOf()
    var listener : PantallaInicialListener? = null

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        comboColor.items.addAll(Color.values())
    }

    fun agregarJugador(actionEvent: ActionEvent) {
        jugadores.add(Jugador(fieldNombre.text, comboColor.value))
        fieldNombre.clear()
        comboColor.items.remove(comboColor.value)
    }

    fun comenzarJuego(actionEvent: ActionEvent) {
        listener?.comenzarJuego(jugadores)
    }

    interface PantallaInicialListener {
        fun comenzarJuego(jugadores: List<Jugador>)
    }
}
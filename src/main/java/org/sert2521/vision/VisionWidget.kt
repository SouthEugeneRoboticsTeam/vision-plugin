@file:JvmName("VisionWidget")

package org.sert2521.vision

import edu.wpi.first.shuffleboard.api.widget.Description
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget
import javafx.beans.property.SimpleIntegerProperty
import javafx.fxml.FXML
import javafx.scene.canvas.Canvas
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import kotlin.math.absoluteValue

@Description(
        name = "B Vision Widget",
        summary = "Displays distance from vision target",
        dataTypes = [Number::class]
)
@ParametrizedController(value = "VisionWidget.fxml")
class VisionWidget : SimpleAnnotatedWidget<Number>() {
    private var cubeDimensions = 50.0
    private var lastOffset = 0.0

    @FXML
    private lateinit var root: Pane

    @FXML
    private lateinit var canvas: Canvas

    private val maxOffset = SimpleIntegerProperty(this, "maxOffset", 640)

    @FXML
    private fun initialize() {
        root.widthProperty().addListener({ _, _, newValue: Number ->
            run {
                canvas.width = newValue.toDouble()
                generateLayover()
            }
        })
        root.heightProperty().addListener({ _, _, newValue: Number ->
            run {
                canvas.height = newValue.toDouble()
                generateLayover()
            }
        })

        dataProperty().addListener({ _, _, newValue -> generateLayover(newValue.toDouble()) })

        generateLayover()

        exportProperties(maxOffset)
    }

    private fun generateLayover(offset: Double? = null) {
        val gc = canvas.graphicsContext2D

        cubeDimensions = (canvas.height + canvas.width) / 10

        clear()

        gc.stroke = Color.WHITE
        gc.strokeLine(canvas.width / 2, 0.0, canvas.width / 2, canvas.height)
        gc.strokeLine(0.0, canvas.height / 2, canvas.width, canvas.height / 2)

        val cubeOffset = if (offset == null) lastOffset else offset / maxOffset.get().absoluteValue * canvas.width

        gc.fill = Color.YELLOW
        gc.fillRect(canvas.width / 2 - cubeDimensions / 2 + cubeOffset, canvas.height / 2 - cubeDimensions / 2, cubeDimensions, cubeDimensions)

        lastOffset = cubeOffset
    }

    private fun clear() = canvas.graphicsContext2D.clearRect(0.0, 0.0, canvas.width, canvas.height)

    override fun getView() = root
}

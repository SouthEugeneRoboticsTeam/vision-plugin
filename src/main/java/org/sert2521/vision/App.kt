package org.sert2521.vision

import com.google.common.collect.ImmutableList
import edu.wpi.first.shuffleboard.api.plugin.Description
import edu.wpi.first.shuffleboard.api.plugin.Plugin
import edu.wpi.first.shuffleboard.api.widget.WidgetType

@Description(
        group = "org.sert2521.vision",
        name = "Vision",
        summary = "Vision widgets for SERT.",
        version = "1.0.0"
)
class App : Plugin() {
    override fun getComponents(): ImmutableList<WidgetType<*>> =
            ImmutableList.of(WidgetType.forAnnotatedWidget(VisionWidget::class.java))
}

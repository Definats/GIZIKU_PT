package com.example.giziku.ui.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.giziku.model.Growth
import me.bytebeats.views.charts.line.LineChart
import me.bytebeats.views.charts.line.LineChartData
import me.bytebeats.views.charts.line.render.line.SolidLineDrawer
import me.bytebeats.views.charts.line.render.point.FilledCircularPointDrawer
import me.bytebeats.views.charts.line.render.xaxis.SimpleXAxisDrawer
import me.bytebeats.views.charts.line.render.yaxis.SimpleYAxisDrawer
import me.bytebeats.views.charts.simpleChartAnimation
import me.bytebeats.views.charts.line.LineChartData.Point

@Composable
fun GrafikPerTanggal(data: List<Growth>) {
    LineChart(
        lineChartData = LineChartData(
            points = data.map { item ->
                val yValue = item.beratBadan.toFloatOrNull() ?: 0f
                val yValue2 = item.tinggiBadan.toFloatOrNull() ?: 0f
                val xLabel = item.tanggal.toString()
                Point(yValue, xLabel)
                Point(yValue2, xLabel)
            }
        ),
        modifier = Modifier.fillMaxSize(),
        animation = simpleChartAnimation(),
        pointDrawer = FilledCircularPointDrawer(),
        lineDrawer = SolidLineDrawer(),
        xAxisDrawer = SimpleXAxisDrawer(),
        yAxisDrawer = SimpleYAxisDrawer(),
        horizontalOffset = 5f
    )
}

@Composable
fun LineChartView() {
    LineChart(
        lineChartData = LineChartData(
            points = listOf(
                Point(40.0F, "Line 1"),
                Point(30.0f, "Line 2"),
            )
        ),
        // Optional properties.
        modifier = Modifier.fillMaxSize(),
        animation = simpleChartAnimation(),
        pointDrawer = FilledCircularPointDrawer(),
        lineDrawer = SolidLineDrawer(),
        xAxisDrawer = SimpleXAxisDrawer(),
        yAxisDrawer = SimpleYAxisDrawer(),
        horizontalOffset = 5f
    )
}


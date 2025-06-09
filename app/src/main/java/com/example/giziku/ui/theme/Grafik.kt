package com.example.giziku.ui.theme

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
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
    // Batasi jumlah data agar grafik tidak terlalu padat
    val recentData = data.takeLast(30)

    LineChart(
        lineChartData = LineChartData(
            points = recentData.flatMap { item ->
                val yValue1 = item.beratBadan.toFloatOrNull() ?: 0f
                val yValue2 = item.tinggiBadan.toFloatOrNull() ?: 0f
                val xLabel = item.tanggal
                listOf(
                    Point(yValue1, xLabel.toString()), // Berat Badan
                    Point(yValue2, xLabel.toString())  // Tinggi Badan
                )
            }
        ),
        modifier = Modifier.fillMaxSize(),
        animation = simpleChartAnimation(),
        pointDrawer = FilledCircularPointDrawer(),
        lineDrawer = SolidLineDrawer(),
        xAxisDrawer = SimpleXAxisDrawer(), // tampilkan 1 dari 3 label
        yAxisDrawer = SimpleYAxisDrawer(),
        horizontalOffset = 5f
    )
}
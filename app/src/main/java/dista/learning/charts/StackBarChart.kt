package dista.learning.charts

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.listener.ChartTouchListener

class StackBarChart : AppCompatActivity() {
    private var targetType: Array<String> = arrayOf("Visits", "Revenue", "Meetings","Accepted", "Leave","Empty","Required")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stack_bar_chart)
        val chart = findViewById<BarChart>(R.id.chart)


        // Sample data for the stacked bar chart
        val entries = listOf(
            BarEntry(0f, floatArrayOf(10f, 20f)), // Two stacks for the first bar
            BarEntry(1f, floatArrayOf(5f, 15f)),  // Two stacks for the second bar
            BarEntry(2f, floatArrayOf(5f, 15f)),  // Two stacks for the second bar
            BarEntry(3f, floatArrayOf(5f, 15f)),  // Two stacks for the second bar
            BarEntry(4f, floatArrayOf(5f, 15f)),  // Two stacks for the second bar
            BarEntry(5f, floatArrayOf(5f, 15f)),  // Two stacks for the second bar
            BarEntry(5f, floatArrayOf(5f, 15f)),  // Two stacks for the second bar
            BarEntry(5f, floatArrayOf(5f, 15f)),  // Two stacks for the second bar
            BarEntry(5f, floatArrayOf(5f, 15f)),  // Two stacks for the second bar
            BarEntry(6f, floatArrayOf(15f, 25f)) // Two stacks for the third bar
        )

        // Creating dataset for the stacked bar
        val dataSet = BarDataSet(entries, "")
        dataSet.colors = listOf(Color.BLUE, Color.GREEN) // Custom colors for the two stacks
        // Combine dataset into BarData
        val data = BarData(dataSet)
        data.barWidth = 0.4f // Adjust the width of the bars
        // Set the data to the chart
        chart.data = data

        // Set the minimum value of the Y-axis to 0
        chart.axisLeft.axisMinimum = 0f
        chart.axisRight.isEnabled=false
        chart.setDrawGridBackground(false)
        chart.description.isEnabled=false
        chart.setDragOffsetX((dataSet.entryCount * 2f))




        // x axis custom
        val xAxis=chart.xAxis
        xAxis.position=XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.valueFormatter=IndexAxisValueFormatter(targetType)
        xAxis.granularity=1f

        val visibleRange = 4f // The number of visible bars on the chart at a time
        chart.setVisibleXRangeMinimum(visibleRange)
        chart.setVisibleXRangeMaximum(visibleRange)


        // y axis custom
        val yAxis=chart.axisLeft
        yAxis.setDrawGridLines(false)

        // legend position
        val legend=chart.legend
        legend.verticalAlignment=Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment=Legend.LegendHorizontalAlignment.RIGHT
        legend.form=Legend.LegendForm.CIRCLE
        legend.formSize=10f

        // tooltip


        // Invalidate the chart to refresh the view
        chart.invalidate()
    }

}

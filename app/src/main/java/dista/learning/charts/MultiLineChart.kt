package dista.learning.charts


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.*
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener


class MultiLineChart : AppCompatActivity() {

    private lateinit var lineChart: LineChart
    private var months: Array<String> =
        arrayOf("January", "February", "March", "April", "May", "June")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_line_chart)

        lineChart = findViewById(R.id.lineChart)

        // Create entries for the four lines
        val entriesLine1 = listOf(
            Entry(0f, 40f),
            Entry(1f, 67f),
            Entry(2f, 89f),
            Entry(3f, 80f),
            Entry(4f, 10f),
            Entry(5f, 34f),
            Entry(6f, 89f)
            // Add more entries as needed
        )

        val entriesLine2 = listOf(
            Entry(0f, 50f),
            Entry(1f, 30f),
            Entry(2f, 70f),
            Entry(3f, 47f),
            Entry(4f, 53f),
            Entry(5f, 46f),
            Entry(6f, 73f)
            // Add more entries as needed
        )

        val entriesLine3 = listOf(
            Entry(0f, 30f),
            Entry(1f, 20f),
            Entry(2f, 40f),
            Entry(3f, 34f),
            Entry(4f, 23f),
            Entry(5f, 64f),
            Entry(6f, 79f)
            // Add more entries as needed
        )


        // Create LineDataSet objects for each line
        val dataSetLine1 = LineDataSet(entriesLine1, "Visits")
        dataSetLine1.color = getColor(R.color.line1)
        dataSetLine1.lineWidth = 3f

        val dataSetLine2 = LineDataSet(entriesLine2, "Revenue")
        dataSetLine2.color = getColor(R.color.line2)
        dataSetLine2.lineWidth = 3f

        val dataSetLine3 = LineDataSet(entriesLine3, "Meetings")
        dataSetLine3.color = getColor(R.color.line3)
        dataSetLine3.lineWidth = 3f


        // Create a LineData object and add the datasets
        val lineData = LineData(dataSetLine1, dataSetLine2, dataSetLine3)


        // Set the LineData to the chart
        lineChart.data = lineData

        lineChart.lineData.setValueTextSize(12f)



        // Customize the chart as needed
        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        lineChart.description.isEnabled = false
        lineChart.axisRight.isEnabled = false
        lineChart.xAxis.setDrawGridLines(false)
        lineChart.setDrawGridBackground(false)
        lineChart.axisLeft.textSize = 12f
        lineChart.setExtraOffsets(0f, 30f, 0f, 0f)


        // Adding the months to the x axis
        lineChart.xAxis.valueFormatter = IndexAxisValueFormatter(months)
        lineChart.xAxis.textSize = 10f
        lineChart.axisLeft.axisMinimum = 0f

        lineChart.xAxis.isGranularityEnabled = true
        lineChart.axisLeft.granularity = 10f

        lineChart.axisRight.isEnabled = false
        lineChart.axisRight.setDrawLabels(false) // Hide right Y-axis values

        lineChart.axisLeft.setDrawAxisLine(false)
        lineChart.axisLeft.setDrawLabels(true)

        lineChart.xAxis.setDrawAxisLine(false)
        lineChart.xAxis.setDrawLabels(true)
        lineChart.xAxis.xOffset=10f


        lineChart.xAxis.granularity=1f


        // Set the grid dashed line for the Y-axis
        val yAxis: YAxis = lineChart.axisLeft
        yAxis.enableGridDashedLine(10f, 10f, 0f)
        yAxis.gridLineWidth = 2f





        // legend code is here
        val legend = lineChart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.yOffset = 0f
        legend.setDrawInside(true)
        legend.textSize = 16f
        legend.form = Legend.LegendForm.CIRCLE
        legend.formSize = 14f // Adjust the size as per your preference
        legend.formLineWidth = 0f
        legend.formLineDashEffect = null


        lineChart.animateXY(900, 900, Easing.EaseInOutBack)

        lineChart.lineData.setValueFormatter(CustomValueFormatter())



        lineChart.isHighlightPerTapEnabled=true
        // Custom marker view
        val mv = CustomMarkView(this, R.layout.mark_view)
        mv.chartView = lineChart
        lineChart.marker = mv
        // Refresh the chart
        lineChart.invalidate()


    }
    inner class CustomValueFormatter : ValueFormatter() {
        override fun getFormattedValue(value: Float): String {
            val spannableString = SpannableString("%.0f".format(value))
            val backgroundColorSpan = BackgroundColorSpan(Color.YELLOW)
            spannableString.setSpan(backgroundColorSpan, 0, spannableString.length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            return spannableString.toString()
        }
    }
}
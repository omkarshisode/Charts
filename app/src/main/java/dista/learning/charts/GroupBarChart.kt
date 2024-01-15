package dista.learning.charts

//import android.graphics.Color
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import java.lang.Math.abs


class GroupBarChart : AppCompatActivity() {

    private lateinit var barChart: BarChart
    private lateinit var barDataSet1: BarDataSet
    private lateinit var barDataSet2: BarDataSet


    // on below line we are creating array list for bar data
    private lateinit var barEntriesList: ArrayList<BarEntry>

    // creating a string array for displaying days.
    private var targetType: Array<String> = arrayOf("Visits", "Revenue", "Meetings","Dummy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_bar_chart)

        barChart = findViewById(R.id.barChart)

        // on below line we are creating a new bar data set
        barDataSet1 = BarDataSet(getBarChartDataForSet1(), "Target")
//        barDataSet1.color=Color

        barDataSet2 = BarDataSet(getBarChartDataForSet2(), "Achieved")
//        barDataSet2.color=Color.parseColor(R.color.bar2.toString())






        // on below line we are adding bar data set to bar data
        val data = BarData(barDataSet1, barDataSet2)
        data.setValueTextSize(12f)


        // Pop up the value that on the top of the bar
        data.isHighlightEnabled=true
        // Pop up the value that on the top of the bar
        barChart.setDrawMarkers(true)
        barChart.data = data

        barChart.description.isEnabled = false

        // X axis configuration
        val xAxis = barChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(targetType)
        xAxis.setCenterAxisLabels(true)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.isGranularityEnabled = true
        xAxis.textSize = 15f
        barChart.xAxis.axisMinimum = 0f
        xAxis.setDrawGridLines(false)
        barChart.setVisibleXRangeMaximum(3f)


        barChart.axisRight.isEnabled = false
        barChart.setDragOffsetX(data.entryCount * 3f)


        // Legend code
        barChart.legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        barChart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        barChart.legend.setDrawInside(true)
        barChart.legend.yOffset = 0f
        barChart.legend.form = Legend.LegendForm.CIRCLE
        barChart.legend.formSize = 14f // Adjust the size as per your preference
        barChart.legend.formLineWidth = 0f
        barChart.legend.formLineDashEffect = null


        barChart.axisLeft.axisMinimum = 0f
        barChart.setScaleEnabled(false)
        barChart.axisLeft.setDrawGridLines(false)
        barChart.axisRight.setDrawGridLines(false)

        xAxis.granularity = 1f
        barChart.axisLeft.granularity = 5f
        barChart.axisLeft.yOffset = 11f


        data.setDrawValues(true)
        val barSpace = 0.01f
        val groupSpace = 0.4f
        data.barWidth = 0.30f

        barChart.animateXY(900,900,Easing.EaseInOutBack)
        barChart.groupBars( 0f,groupSpace, barSpace)
        barChart.setExtraOffsets(0f, 40f, 0f, 5f)

        barChart.barData.setValueFormatter(CustomValueFormatter()) // customize the value to the float
        barChart.setDrawMarkers(true)

        // it shows the value above the bar
        barChart.setDrawValueAboveBar(true)
        // using this we can pop up the tooltip
        barChart.isHighlightPerTapEnabled=true

        // Custom marker view
        val mv = CustomMarkView(this, R.layout.mark_view)
        mv.chartView = barChart
        barChart.marker = mv
        barChart.setDrawBarShadow(false)


        barChart.invalidate()

    }

    private fun getBarChartDataForSet1(): ArrayList<BarEntry> {
        barEntriesList = ArrayList()

        barEntriesList.add(BarEntry(1f, 20f))
        barEntriesList.add(BarEntry(2f, 20f))
        barEntriesList.add(BarEntry(3f, 20f))

        return barEntriesList
    }

    private fun getBarChartDataForSet2(): ArrayList<BarEntry> {
        barEntriesList = ArrayList()

        barEntriesList.add(BarEntry(1f, 18f))
        barEntriesList.add(BarEntry(2f, 18f))
        barEntriesList.add(BarEntry(3f, 18f))

        return barEntriesList

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
package dista.learning.charts

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import java.text.DecimalFormat

class CustomMarkView(context: Context, layoutResource:Int):
 MarkerView(context, layoutResource){

    private val textView: TextView = findViewById(R.id.tvContent)
    private val format: DecimalFormat = DecimalFormat("")

    // Adjust these values to displace the tooltip
    private val xOffset = 0f // Horizontal displacement
    private val yOffset = -height.toFloat() - 20f // Vertical displacement

    override fun refreshContent(entry: Entry?, highlight: Highlight?) {
        val value = entry?.y ?: 0f
        textView.text = format.format(value)
        super.refreshContent(entry, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF(xOffset, yOffset)
    }
}
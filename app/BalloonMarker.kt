import android.content.Context

import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry

class CustomMarkerView(context: Context, layoutResource: Int) : MarkerView(context, layoutResource) {

//    private val tvContent: TextView = findViewById(R.id.tvContent)

private lateinit var tvContent:TextView


    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        if (e == null) return


        // Show the value as a tooltip
        tvContent.text = "%.0f".format(e.y)

        super.refreshContent(e, highlight)
    }

    override fun getXOffset(xpos: Float): Int {
        return -(width / 2)
    }

    override fun getYOffset(ypos: Float): Int {
        return -height
    }
}

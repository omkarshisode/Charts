package dista.learning.charts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var btnGroupBarChart:Button
    lateinit var btnMultiLineChart:Button
    lateinit var btnStackBarChart: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGroupBarChart=findViewById(R.id.btnGroupBarChart)
        btnMultiLineChart=findViewById(R.id.btnMultiLineChart)
        btnStackBarChart=findViewById(R.id.btnStackBar)

        btnGroupBarChart.setOnClickListener {
           startActivity(Intent(this, GroupBarChart::class.java))
        }

        btnMultiLineChart.setOnClickListener {
            startActivity(Intent(this, MultiLineChart::class.java))
        }

        btnStackBarChart.setOnClickListener {
            startActivity(Intent(this, StackBarChart::class.java))
        }
    }
}
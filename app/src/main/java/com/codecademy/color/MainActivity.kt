package com.codecademy.color

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {
    lateinit var bitmap: Bitmap
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val image: ImageView = findViewById(R.id.pickColorImage)
        val colorView: View = findViewById(R.id.fillColor)
        var colorString: TextView = findViewById(R.id.colorInHex)
        var colorPicked: TextView = findViewById(R.id.colorPicked_tv)
        val red_sBar: SeekBar = findViewById(R.id.red_seekBar)
        val green_sBar: SeekBar = findViewById(R.id.green_seekBar)
        val blue_sBar: SeekBar = findViewById(R.id.blue_seekBar)
        val tvRed: TextView = findViewById(R.id.redNum_textView)
        val tvGreen: TextView = findViewById(R.id.greenNum_textView)
        val tvBlue: TextView = findViewById(R.id.blueNum_textView)
        val clearBtn: Button = findViewById(R.id.clear_btn)
        var pixel : Int
        clearBtn.visibility = View.INVISIBLE

        var red = 255
        var green = 255
        var blue = 255
        var myColor = Color.rgb(red, green, blue)

        image.isDrawingCacheEnabled = true
        image.buildDrawingCache(true)

        // on touch listener on image view
        image.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE) {
                bitmap = image.drawingCache
                // get touched pixel
                pixel = bitmap.getPixel(event.x.toInt(), event.y.toInt())
                // get RGB values from the touched pixel
                val r = Color.red(pixel)
                val g = Color.green(pixel)
                val b = Color.blue(pixel)
                // color name in Hexadecimal(#RRGGBB) and color
                colorString.text = String.format("#%06X", (0xFFFFFF and pixel))
                colorString.setTextColor(pixel)
                // fill the color in the view
                colorView.setBackgroundColor(Color.rgb(r, g, b))
                colorPicked.text = "Color picked using wheel"
                clearBtn.visibility = View.VISIBLE

            }
            true
        }



        fun updateColor() {
            myColor = Color.rgb(red, green, blue)
            colorView.setBackgroundColor(myColor)
            colorString.text = String.format("#%06X", (0xFFFFFF and myColor))
            colorPicked.text = "Color picked using sliders (RGB)"
            colorString.setTextColor(myColor)
            clearBtn.visibility = View.VISIBLE

        }



        red_sBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, newRed: Int, p2: Boolean) {
                tvRed.text = newRed.toString()
                red = newRed
                updateColor()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
        green_sBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, newGreen: Int, p2: Boolean) {
                tvGreen.text = newGreen.toString()
                green = newGreen
                updateColor()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
        blue_sBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, newBlue: Int, p2: Boolean) {
                tvBlue.text = newBlue.toString()
                blue = newBlue
                updateColor()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        clearBtn.setOnClickListener {
            red_sBar.progress = 0
            green_sBar.progress = 0
            blue_sBar.progress = 0
            tvRed.text = ""
            tvBlue.text = ""
            tvGreen.text = ""
            colorView.setBackgroundColor(Color.WHITE) // Reset to white or any default color
            colorString.text = "#FFFFFF" // Resetting the color hex string to white
            red = 0
            green = 0
            blue = 0
            updateColor()
            colorPicked.text = "Pick a color"

        }

    }
}

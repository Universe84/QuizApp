package com.example.buttonclicker

import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock.sleep
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.animation.ObjectAnimator
import android.graphics.Path
import android.graphics.RectF
import android.os.Build
import android.view.View
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation



class MainActivity : AppCompatActivity() {

    //lateinit is a promise that you will initialize the variable before it is used or it will crash
    private lateinit var button : Button
    private var score: Int = 0
    private lateinit var textView_main_score : TextView
    private lateinit var layout: ConstraintLayout
    private lateinit var color : Color

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets



        }




        button = findViewById(R.id.button_main_clicker)
        layout = findViewById(R.id.main)
        textView_main_score = findViewById(R.id.textView_main_score)
        textView_main_score.textSize = 100.0F
        layout.setBackgroundColor(12345)
        button.setOnClickListener {
            score++
            textView_main_score.text = score.toString()
            if(score % 5 == 0) {

                 ObjectAnimator.ofFloat(textView_main_score, "rotationY", textView_main_score.rotationY + 360f).apply{
                    duration = 1000
                    start()
                }






                Toast.makeText(this, "Clicked: $score times!!!!", Toast.LENGTH_SHORT).show()
            }


            if(score % 10 == 0){
                val path = Path().apply {
                    val apple = RectF(textView_main_score.x, textView_main_score.y, 500f, 700f)
                    arcTo(apple , 270f, -360f, true )
                }
                ObjectAnimator.ofFloat(textView_main_score, View.X, View.Y, path).apply{
                    duration = 2000
                    start()
                }

            }


            }
        }



    }

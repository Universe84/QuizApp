package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var tVButtonLeft : TextView
    private lateinit var tVButtonRight : TextView
    private lateinit var tVScore : TextView
    private var score = 0
    private val MAX_NUMBER = 100
    private val MIN_NUMBER = 1
    private var randomNumLeft = Random.nextInt(MIN_NUMBER, MAX_NUMBER)
    private var randomNumRight = Random.nextInt(MIN_NUMBER, MAX_NUMBER)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tVButtonLeft = findViewById(R.id.textView_button_left)
        tVButtonRight = findViewById(R.id.textView_button_right)
        tVScore = findViewById(R.id.textView_score)
        tVScore.text = score.toString()
        tVButtonRight.text = randomNumRight.toString()
        tVButtonLeft.text = randomNumLeft.toString()

        tVButtonLeft.setOnClickListener(){
            if(randomNumLeft >= randomNumRight){
                score++
                tVScore.text = score.toString()

            }
            randomNumLeft = Random.nextInt(MIN_NUMBER, MAX_NUMBER)
            randomNumRight = Random.nextInt(MIN_NUMBER, MAX_NUMBER)
            tVButtonLeft.text = randomNumLeft.toString()
            tVButtonLeft.text = randomNumRight.toString()


        }

        tVButtonRight.setOnClickListener(){
            if(randomNumRight >= randomNumLeft){
                score++
                tVScore.text = score.toString()

            }
            randomNumLeft = Random.nextInt(MIN_NUMBER, MAX_NUMBER)
            randomNumRight = Random.nextInt(MIN_NUMBER, MAX_NUMBER)
            tVButtonLeft.text = randomNumLeft.toString()
            tVButtonLeft.text = randomNumRight.toString()


        }


    }
}
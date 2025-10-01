package com.example.whichisbigger

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
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
    private var randomNumLeftOther = Random.nextInt(MIN_NUMBER, MAX_NUMBER)
    private var randomNumRight = Random.nextInt(MIN_NUMBER, MAX_NUMBER)
    private var randomNumRightOther = Random.nextInt(MIN_NUMBER, MAX_NUMBER)
    private var rightProduct = randomNumRight * randomNumRightOther
    private var leftProduct = randomNumLeft * randomNumLeftOther

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
        tVScore.textSize = 100f
        tVButtonRight.textSize = 40f
        tVButtonLeft.textSize = 40f
        tVButtonRight.text = "$randomNumRight X $randomNumRightOther"
        tVButtonLeft.text = "$randomNumLeft X $randomNumLeftOther"

        fun makeRandomNums(){
            randomNumLeft = Random.nextInt(MIN_NUMBER, MAX_NUMBER)
            randomNumLeftOther = Random.nextInt(MIN_NUMBER, MAX_NUMBER)
            randomNumRight = Random.nextInt(MIN_NUMBER, MAX_NUMBER)
            randomNumRightOther = Random.nextInt(MIN_NUMBER, MAX_NUMBER)
            rightProduct = randomNumRight * randomNumRightOther
            leftProduct = randomNumLeft * randomNumLeftOther
            if(rightProduct == leftProduct || Math.abs(rightProduct - leftProduct) > 50){
                makeRandomNums()
            }
            tVButtonRight.text = "$randomNumRight X $randomNumRightOther"
            tVButtonLeft.text = "$randomNumLeft X $randomNumLeftOther"
        }

        makeRandomNums()

        tVButtonLeft.setOnClickListener(){
            if(leftProduct > rightProduct) {
                score++
                tVScore.text = score.toString()

            }
            makeRandomNums()


        }

        tVButtonRight.setOnClickListener(){
            if(leftProduct < rightProduct){
                score++
                tVScore.text = score.toString()

            }
            makeRandomNums()


        }


    }

    
}
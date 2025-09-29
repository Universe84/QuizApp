package com.example.quizapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import kotlin.math.abs

class LearningJson : AppCompatActivity() {
    companion object {
        val TAG = "LearningJson"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_learning_json)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val gson = Gson()
        val inputStream = resources.openRawResource(R.raw.pluslife)
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        val test = gson.fromJson(jsonString, PlusLifeTest::class.java)
        Log.d(TAG, "onCreate: $test")

        var maxValue: Double = Double.MIN_VALUE
        var minValue: Double = Double.MAX_VALUE
        var avgValue: Double = 0.0
        var largestDifference: Double = 0.0
        var largestDifferenceIndex: Int = 0
        var targetTemp: Double = test.targetTemp
        var numOfTempsThatWereOff: Int = 0
        for (tempSample in test.testData.temperatureSamples) {
            var totals: Double = 0.0
            if (maxValue < tempSample.temp) {
                maxValue = tempSample.temp
            }
            if (minValue > tempSample.temp) {
                minValue = tempSample.temp
            }
            if (abs(tempSample.temp - targetTemp) > 0.5) {
                numOfTempsThatWereOff++
            }
            avgValue += tempSample.temp
        }
        for (i in test.testData.temperatureSamples.indices) {
            if (abs(test.testData.temperatureSamples[i].temp - targetTemp) > largestDifference) {
                largestDifference = test.testData.temperatureSamples[i].temp - targetTemp
                largestDifferenceIndex = i
            }



        }
        avgValue /= test.testData.temperatureSamples.size
        Log.d(
            TAG,
            "onCreate : $maxValue, $minValue, $avgValue, $largestDifference, $numOfTempsThatWereOff, $largestDifferenceIndex"
        )

    }
}
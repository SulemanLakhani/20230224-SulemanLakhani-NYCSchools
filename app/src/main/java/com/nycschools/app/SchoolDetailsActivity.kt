package com.nycschools.app

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SchoolDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_details)
        val abnView : TextView = findViewById(R.id.abn)
        val nameView : TextView = findViewById(R.id.name)
        val addressView : TextView = findViewById(R.id.address)
        val cityView : TextView = findViewById(R.id.city)
        val zipView : TextView = findViewById(R.id.zip)
        val stateView : TextView = findViewById(R.id.stateCode)
        val webView : TextView = findViewById(R.id.webSite)
        val phoneView : TextView = findViewById(R.id.phoneNumber)
        val testTakersView : TextView = findViewById(R.id.testTakers)
        val readingAvgScoreView : TextView = findViewById(R.id.readingAvgScore)
        val mathAvgScoreView : TextView = findViewById(R.id.mathAvgScore)
        val writingAvgScoreView : TextView = findViewById(R.id.writingAvgScore)

        val school : School? = intent.getParcelableExtra (MainActivity.SECOND_ACTIVITY_CODE)

        abnView.text = school?.dbn
        nameView.text = school?.name
        addressView.text = school?.address
        cityView.text = school?.city
        zipView.text = school?.zip
        stateView.text = school?.stateCode
        webView.text = school?.webSite
        phoneView.text = school?.phoneNumber
        val satScore = SatViewModel().getSatDetails(school)
        testTakersView.text = satScore?.testTakers.toString()
        readingAvgScoreView.text = satScore?.readingAvgScore.toString()
        mathAvgScoreView.text = satScore?.mathAvgScore.toString()
        writingAvgScoreView.text = satScore?.writingAvgScore.toString()
    }
}



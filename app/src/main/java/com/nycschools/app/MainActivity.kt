package com.nycschools.app

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    companion object{
        // Key that pass with intent
        var SECOND_ACTIVITY_CODE="second_activity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView : ListView = findViewById(R.id.listView)
        val schoolsList = SchoolViewModel().getSchoolDetails()
        val schoolsTitlesList = SchoolViewModel().getSchoolTitles(schoolsList)

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, schoolsTitlesList)
        listView.setOnItemClickListener { _, _, position, _ ->

            val intent = Intent(this, SchoolDetailsActivity::class.java)
            val schoolDetails = schoolsList[position]
            intent.putExtra(SECOND_ACTIVITY_CODE,schoolDetails)
            startActivity(intent)
        }
        listView.adapter = arrayAdapter

    }
}

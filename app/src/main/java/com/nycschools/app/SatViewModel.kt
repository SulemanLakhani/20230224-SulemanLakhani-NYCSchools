package com.nycschools.app

import androidx.lifecycle.ViewModel
import org.json.JSONArray
import org.json.JSONTokener
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class SatViewModel : ViewModel() {
    fun getSatDetails(school : School?) : SatScore? {
        var satScore : SatScore?= null
        val t1 = Thread {
            try {
                val url = URL("https://data.cityofnewyork.us/resource/f9bf-2cp4.json")
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.setRequestProperty("Content-Type", "application/json")
                val reader = BufferedReader(InputStreamReader(BufferedInputStream(connection.inputStream)))
                val sb = StringBuilder()
                var line: String?
                while (true) {
                    line = reader.readLine()
                    if (line != null)
                        sb.append(line).append("\n")
                    else
                        break
                }
                val jsonArray = JSONTokener(sb.toString()).nextValue() as JSONArray

                for (i in 0 until jsonArray.length()) {
                    val jsonObject  = jsonArray.getJSONObject(i)
                    val dbn  = jsonObject.getString("dbn")
                    if(school?.dbn .equals(dbn)) {
                        satScore = SatScore(jsonObject.getString("dbn"),
                            jsonObject.getString("school_name"),
                            jsonObject.getInt("num_of_sat_test_takers"),
                            jsonObject.getInt("sat_critical_reading_avg_score"),
                            jsonObject.getInt("sat_math_avg_score"),
                            jsonObject.getInt("sat_writing_avg_score"))
                    }
                }
                reader.close()
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
        t1.start()
        Thread.sleep(500)
        return satScore
    }
}
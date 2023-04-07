package com.nycschools.app

import androidx.lifecycle.ViewModel
import org.json.JSONArray
import org.json.JSONTokener
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class SchoolViewModel : ViewModel() {

    fun getSchoolDetails() : List<School> {
        val schoolsList : ArrayList<School> = ArrayList()
        val t1 = Thread {
            try {
                val url = URL("https://data.cityofnewyork.us/resource/s3k6-pzi2.json")
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
                    val dbn : String = jsonObject.getString("dbn")
                    val name: String  = jsonObject.getString("school_name")
                    val address : String = jsonObject.getString("location")
                    val city : String = jsonObject.getString("city")
                    val zip : String = jsonObject.getString("zip")
                    val stateCode : String = jsonObject.getString("school_name")
                    val webSite : String = jsonObject.getString("website")
                    val phoneNumber : String = jsonObject.getString("phone_number")
                    schoolsList.add(School(dbn,name,address,city,zip,stateCode,webSite,phoneNumber))
                }
                reader.close()
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
        t1.start()
        t1.join()
        return schoolsList
    }

    fun getSchoolTitles(schoolsList : List<School>) : List<String>{
        val schoolsTitlesList : ArrayList<String>  = ArrayList()
        for(i in schoolsList.indices){
            schoolsList[i].name?.let { schoolsTitlesList.add(it) }
        }
        return schoolsTitlesList
    }

}
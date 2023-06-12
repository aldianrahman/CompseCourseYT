@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.example.compsecourseyt

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.compsecourseyt.ui.HomeScreen
import com.example.compsecourseyt.ui.theme.MeditationUIYouTubeTheme
import com.google.gson.JsonObject
import com.koushikdutta.ion.Ion
import java.util.Calendar


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val TAG: String = "JSON_MONGODB"
            val API_KEY: String = "6438f8553f4da8ec3781253b"
            val findOne: String = "/action/findOne"
            val url =
                "https://ap-southeast-1.aws.data.mongodb-api.com/app/data-oukpf/endpoint/data/v1$findOne"
            val sendObject = JsonObject()
            var solution = ""

            sendObject.addProperty("dataSource", "Cluster0")
            sendObject.addProperty("database", "todo")
            sendObject.addProperty("collection", "traning")


            Ion.with(context)
                .load(url)
                .setHeader("Content-Type", "application/json")
                .setHeader("Access-Control-Request-Headers", "*")
                .setHeader("Accept", "application/json")
                .setJsonObjectBody(sendObject)
                .asJsonObject()
                .setCallback { e, result ->
                    if (e != null) {
                        Log.i(TAG, "getApiMongodb Error: $e")
                    } else {
                        Log.i(TAG, "getApiMongodb Success: $result")
                        if (result != null) {
                            val document = result.getAsJsonObject("document")
                            val solutions = document.getAsJsonObject("solutions")
                            solution = solutions["solution"].asString
                            Log.i(TAG, "Solution: $solution")
                            Toast.makeText(context, ""+solution, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            MainPreview()
        }
    }
}





@SuppressLint("SuspiciousIndentation")
@Preview(showBackground = true)
@Composable
fun MainPreview() {
    MeditationUIYouTubeTheme {

        val calendar = Calendar.getInstance()
        val date = calendar.get(Calendar.DATE)

        val dayOfWeekString = when (calendar.get(Calendar.DAY_OF_WEEK)) {
            Calendar.SUNDAY -> "Sunday"
            Calendar.MONDAY -> "Monday"
            Calendar.TUESDAY -> "Tuesday"
            Calendar.WEDNESDAY -> "Wednesday"
            Calendar.THURSDAY -> "Thursday"
            Calendar.FRIDAY -> "Friday"
            Calendar.SATURDAY -> "Saturday"
            else -> "Unknown"
        }
        val monthNames = arrayOf(
            "January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"
        )
        val month = calendar.get(Calendar.MONTH) + 1 // Note: Months start from 0
        val year = calendar.get(Calendar.YEAR)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        var inDay = ""
        var wish = ""

        inDay = when (hour) {
            in 6..11 -> "Morning"
            in 12..17 -> "Afternoon"
            else -> "Night"
        }

        wish = when (hour) {
            in 6..11 -> "May your day bring happiness and success!"
            in 12..13 -> "The time for rest and prayer has come!"
            in 12..17 -> "Rediscover your motivation to get back to work!"
            else -> "It's time for a break, relax yourself!"
        }

        val name = "Aldian Rahman"
        val salam = "Good $inDay, $name"
        val harapan = wish


            HomeScreen(salam,harapan,"$dayOfWeekString, $date "+monthNames[month]+" $year")

    }

}


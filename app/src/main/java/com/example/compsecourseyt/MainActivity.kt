@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.example.compsecourseyt

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compsecourseyt.ui.HomeScreen
import com.example.compsecourseyt.ui.theme.MeditationUIYouTubeTheme
import com.google.gson.JsonObject
import com.koushikdutta.ion.Ion
import kotlinx.coroutines.delay
import java.util.Calendar


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun SplashScreen(navigateToNextScreen: () -> Unit) {
    LaunchedEffect(key1 = true) {
        delay(5000) // Menunggu 3 detik sebelum melanjutkan ke layar berikutnya
        navigateToNextScreen()
    }

    // Tampilan SplashScreen
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Splash Screen",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun App() {

    val context = LocalContext.current
    val TAG: String = "JSON_MONGODB"
    val API_KEY: String = "6438f8553f4da8ec3781253b"
    val findOne: String = "/action/findOne"
    val url =
        "https://ap-southeast-1.aws.data.mongodb-api.com/app/data-oukpf/endpoint/data/v1$findOne"
    val sendObject = JsonObject()
    var solution = ""
    var email = ""

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
                }
            }
        }

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
                    email = solutions["email"].asString
                    Log.i(TAG, "Solution: $solution")
                }
            }
        }

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navigateToNextScreen = { navController.navigate("main") })
        }
        composable("main") {
            MainPreview(solution,email)
        }
    }
}




@SuppressLint("SuspiciousIndentation")
//@Preview(showBackground = true)
@Composable
fun MainPreview(
    data: String,
    email: String
) {
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


            HomeScreen(salam,harapan,"$dayOfWeekString, $date "+monthNames[month]+" $year",data,email)

    }

}


@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.example.compsecourseyt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.compsecourseyt.ui.HomeScreen
import com.example.compsecourseyt.ui.theme.MeditationUIYouTubeTheme
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainPreview()
        }
    }
}





@Preview(showBackground = true)
@Composable
fun MainPreview() {
    MeditationUIYouTubeTheme {

        val calendar = Calendar.getInstance()
        val date = calendar.get(Calendar.DATE)
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        val dayOfWeekString = when (dayOfWeek) {
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


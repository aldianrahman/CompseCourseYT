@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.example.compsecourseyt

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.compsecourseyt.transport.IonMaster
import com.example.compsecourseyt.transport.MainTransport
import com.example.compsecourseyt.ui.HomeScreen
import com.example.compsecourseyt.ui.SecondScreen
import com.example.compsecourseyt.ui.theme.MeditationUIYouTubeTheme
import com.google.gson.JsonObject
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
    var expand by remember{
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        delay(1500)
        expand = !expand
        delay(3000) // Menunggu 3 detik sebelum melanjutkan ke layar berikutnya
        navigateToNextScreen()
    }

    // Tampilan SplashScreen
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Card(
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.background(Color.White)
            ) {
                Image(painterResource(R.drawable.jetpack_compose),
                    contentDescription = null,
                    modifier = Modifier.background(Color.White)
                )
                AnimatedVisibility(expand){
                    Text(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        text = "Jetpack Compose",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.background(Color.White)
                    )
                }
            }
        }
    }
}



@Composable
fun App() {

    val context = LocalContext.current
    val TAG: String = "JSON_MONGODB"
    var mainTransport = MainTransport()
    var names = ""
    var email = ""
    val stringButton = mutableListOf<String>()
    val stringFeature = mutableListOf<String>()

    mainTransport.getData(context, object : IonMaster.IonCallback {
        override fun onReadyCallback(errorMessage: String?, `object`: Any?) {
            var result = `object`
            if (errorMessage != null) {
                Log.i(TAG, "getApiMongodb Error: $errorMessage")
            } else {
                Log.i(TAG, "getApiMongodb Success: $result")
                if (result != null) {
                    result as JsonObject
                    val document = result.getAsJsonObject("document")
                    val data_user = document.getAsJsonObject("data_user")
                    val buttonString = document.getAsJsonArray("listButton")
                    val featureString = document.getAsJsonArray("listFeature")
                    names = data_user["name"].asString
                    email = data_user["email"].asString
                    Log.i(TAG, "Solution: $names")
                    Log.i(TAG, "Button String: $buttonString")

                    for (i in 0 until buttonString.size()) {
                        val button = buttonString.get(i).asString
                        stringButton.add(button)
                        Log.d("Button", button)
                    }

                    for (i in 0 until featureString.size()) {
                        val feature = featureString.get(i).asString
                        stringFeature.add(feature)
                        Log.d("Feature", feature)
                    }
                }
            }
        }
    })

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navigateToNextScreen = { navController.navigate(Screen.MainScreen.route) })
        }
        composable(Screen.MainScreen.route) {
            MainScreen(names,email,stringButton,stringFeature,navController)
        }
        composable(
            Screen.SecondScreen.route+ "/{index}",
            arguments = listOf(
                navArgument("index"){
                    type = NavType.StringType
                    defaultValue = "Error Data"
                    nullable = true
                }
            )
        ){entry->
            SecondScreen(entry.arguments?.getString("index"))
        }
    }
}




@SuppressLint("SuspiciousIndentation")
//@Preview(showBackground = true)
@Composable
fun MainScreen(
    names: String,
    email: String,
    stringButton: List<String>,
    stringFeature: MutableList<String>,
    navController: NavHostController
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


            HomeScreen(salam,harapan,
                "$dayOfWeekString, $date "+monthNames[month]+" $year",
                names,
                email,
                stringButton,
                stringFeature,
                navController
            )

    }

}


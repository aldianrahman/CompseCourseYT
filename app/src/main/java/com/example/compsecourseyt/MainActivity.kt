package com.example.compsecourseyt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import com.example.compsecourseyt.ui.HomeScreen
import com.example.compsecourseyt.ui.theme.MeditationUIYouTubeTheme
import kotlinx.coroutines.launch
import kotlin.random.Random

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
        HomeScreen()
    }

}


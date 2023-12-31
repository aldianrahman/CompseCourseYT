package com.example.compsecourseyt.ui

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.compsecourseyt.BottomMenuContent
import com.example.compsecourseyt.Feature
import com.example.compsecourseyt.R
import com.example.compsecourseyt.Screen
import com.example.compsecourseyt.standardQuadFromTo
import com.example.compsecourseyt.ui.theme.AquaBlue
import com.example.compsecourseyt.ui.theme.Beige1
import com.example.compsecourseyt.ui.theme.Beige2
import com.example.compsecourseyt.ui.theme.Beige3
import com.example.compsecourseyt.ui.theme.Blue1
import com.example.compsecourseyt.ui.theme.Blue2
import com.example.compsecourseyt.ui.theme.Blue3
import com.example.compsecourseyt.ui.theme.BlueViolet1
import com.example.compsecourseyt.ui.theme.BlueViolet2
import com.example.compsecourseyt.ui.theme.BlueViolet3
import com.example.compsecourseyt.ui.theme.ButtonBlue
import com.example.compsecourseyt.ui.theme.DarkerButtonBlue
import com.example.compsecourseyt.ui.theme.DeepBlue
import com.example.compsecourseyt.ui.theme.Green1
import com.example.compsecourseyt.ui.theme.LightGreen1
import com.example.compsecourseyt.ui.theme.LightGreen2
import com.example.compsecourseyt.ui.theme.LightGreen3
import com.example.compsecourseyt.ui.theme.OrangeYellow1
import com.example.compsecourseyt.ui.theme.OrangeYellow2
import com.example.compsecourseyt.ui.theme.OrangeYellow3
import com.example.compsecourseyt.ui.theme.Red1
import com.example.compsecourseyt.ui.theme.Red2
import com.example.compsecourseyt.ui.theme.Red3
import com.example.compsecourseyt.ui.theme.TextWhite


@Composable
fun HomeScreen(
    salam: String,
    harapan: String,
    date: String,
    data: String,
    email: String,
    stringButton: List<String>,
    stringFeature: MutableList<String>,
    navController: NavHostController
){
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ){
        Column {
            GreetingSection(salam,harapan,date)
            ChipSection(chips = stringButton)
            CurrentMeditation(data,email)
            FeatureSection(
                features = listOf(
                    Feature(
                        title = stringFeature[0],
                        R.drawable.ic_headphone,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3
                    ),
                    Feature(
                        title = stringFeature[1],
                        R.drawable.ic_videocam,
                        LightGreen1,
                        LightGreen2,
                        LightGreen3
                    ),
                    Feature(
                        title = stringFeature[2],
                        R.drawable.ic_headphone,
                        OrangeYellow3,
                        OrangeYellow2,
                        OrangeYellow1,

                    ),
                    Feature(
                        title = stringFeature[3],
                        R.drawable.ic_headphone,
                        Beige1,
                        Beige2,
                        Beige3
                    ),
                    Feature(
                        title = stringFeature[4],
                        R.drawable.ic_headphone,
                        Red1,
                        Red2,
                        Red3
                    ),
                    Feature(
                        title = stringFeature[5],
                        R.drawable.ic_headphone,
                        Blue1,
                        Blue2,
                        Blue3
                    )
                )
            )
        }
        BottomMenu(
            items = listOf(
            BottomMenuContent("Home", R.drawable.ic_home),
            BottomMenuContent("Meditate", R.drawable.ic_bubble),
            BottomMenuContent("Sleep", R.drawable.ic_moon),
            BottomMenuContent("Music", R.drawable.ic_music),
            BottomMenuContent("Profile", R.drawable.ic_profile),
        ), modifier = Modifier.align(Alignment.BottomCenter)
        , navController = navController
        )
    }
}

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0,
    navController: NavHostController?
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(5.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index
                if (selectedItemIndex == 0){
                    navController?.navigate(Screen.MainScreen.route)
                }else if (selectedItemIndex == 1){
                    navController?.navigate(Screen.SecondScreen.withArgs(selectedItemIndex.toString()))
                }else if (selectedItemIndex == 2){
                    navController?.navigate(Screen.SplashScreen.route)
                }
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconID),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = item.title,
            color = if(isSelected) activeTextColor else inactiveTextColor
        )
    }
}

@Composable
fun GreetingSection(
    greeting: String,
    wish: String,
    date: String
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp, horizontal = 15.dp)
    ){
        Column(
            verticalArrangement = Arrangement.Center,

        ) {
            Text(
                date,
                style = MaterialTheme.typography.bodySmall,
                color = TextWhite
            )
            Text(
                greeting,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                wish,
                style = MaterialTheme.typography.bodyLarge
            )

        }
        Icon(
            painter = painterResource(R.drawable.ic_search),
            contentDescription ="Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ChipSection(
    chips: List<String>
){
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    val context = LocalContext.current
    LazyRow {
        items(chips.size){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable {
                        selectedChipIndex = it
                        Toast.makeText(context, ""+chips[selectedChipIndex], Toast.LENGTH_SHORT).show()
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) ButtonBlue
                    else DarkerButtonBlue
                    )
                    .padding(15.dp)
            ){
                Text(
                    text = chips[it],
                    color= TextWhite
                    )
            }
        }
    }
}

@Composable
fun CurrentMeditation(
    data: String,
    email: String
){
    val contex = LocalContext.current
//    BoxWithConstraints(
//        contentAlignment = Alignment.Center,
//        modifier = Modifier
//            .padding(15.dp)
//            .clip(RoundedCornerShape(10.dp))
//            .background(Red3)
//            .padding(horizontal = 15.dp, vertical = 20.dp).fillMaxWidth().height(40.dp)
//    ){
//        val width = constraints.maxWidth
//        val height = constraints.maxHeight
//
//        //Medium colored path
//        val mediumColoredPoint1 = Offset(0f,height * 0.3f)
//        val mediumColoredPoint2 = Offset(width * 0.1f,height * 0.35f)
//        val mediumColoredPoint3 = Offset(width * 0.4f,height * 0.05f)
//        val mediumColoredPoint4 = Offset(width *0.7f,height * 0.6f)
//        val mediumColoredPoint5 = Offset(width * 1.4f,-height.toFloat())
//
//        val mediumColoredPath = Path().apply {
//            moveTo(mediumColoredPoint1.x,mediumColoredPoint2.y)
//            standardQuadFromTo(mediumColoredPoint1,mediumColoredPoint2)
//            standardQuadFromTo(mediumColoredPoint2,mediumColoredPoint3)
//            standardQuadFromTo(mediumColoredPoint3,mediumColoredPoint4)
//            standardQuadFromTo(mediumColoredPoint4,mediumColoredPoint5)
//            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
//            lineTo(-100f, height.toFloat() + 100f)
//            close()
//        }
//
//        // Light colored path
//        val lightPoint1 = Offset(0f, height * 0.35f)
//        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
//        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
//        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
//        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)
//
//        val lightColoredPath = Path().apply {
//            moveTo(lightPoint1.x, lightPoint1.y)
//            standardQuadFromTo(lightPoint1, lightPoint2)
//            standardQuadFromTo(lightPoint2, lightPoint3)
//            standardQuadFromTo(lightPoint3, lightPoint4)
//            standardQuadFromTo(lightPoint4, lightPoint5)
//            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
//            lineTo(-100f, height.toFloat() + 100f)
//            close()
//        }
//
//        Canvas(
//            modifier = Modifier
//                .fillMaxSize()
//        ){
//            drawPath(
//                path = mediumColoredPath,
//                color = Red2
//            )
//            drawPath(
//                path = lightColoredPath,
//                color = Red1
//            )
//        }
//    }

    val gradientColors = listOf(

        Blue1,
        Red1,
        OrangeYellow1,
        Green1

    )

    val gradient = Brush.verticalGradient(gradientColors)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(gradient)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color.Unspecified,
                            Color.Transparent
                        ),
                        startX = 0.1f
                    )
                )
        ) {
            Text(
                text = email,
                style = MaterialTheme.typography.headlineMedium.copy(
                    shadow = Shadow(
                        color = Color.Gray,
                        offset = Offset(2f, 2f),
                        blurRadius = 4f
                    )
                )
            )
            Text(
                text = data,
                color = TextWhite,
                style = MaterialTheme.typography.bodyLarge.copy(
                    shadow = Shadow(
                        color = Color.Gray,
                        offset = Offset(1f,1f),
                        blurRadius = 2f
                    )
                )
            )
        }


        Icon(
            painter = painterResource(R.drawable.ic_play),
            contentDescription = "Play",
            tint = Color.White,
            modifier = Modifier
                .size(16.dp)
                .clickable {
                    Toast.makeText(contex, "Music Play", Toast.LENGTH_SHORT).show()
                }
        )
    }

}

@Composable
fun FeatureSection(
    features: List<Feature>
){
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            "Features",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 15.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ){
            items(
                features.size
            ){
                FratureItem(
                    feature = features[it]
                )
            }
        }
    }
}

@Composable
fun FratureItem(
    feature: Feature
){
    val contex = LocalContext.current
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ){
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        //Medium colored path
        val mediumColoredPoint1 = Offset(0f,height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f,height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f,height * 0.05f)
        val mediumColoredPoint4 = Offset(width *0.7f,height * 0.6f)
        val mediumColoredPoint5 = Offset(width * 1.4f,-height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPoint1.x,mediumColoredPoint2.y)
            standardQuadFromTo(mediumColoredPoint1,mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2,mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3,mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4,mediumColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        // Light colored path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ){
            drawPath(
                path = mediumColoredPath,
                color = feature.mediumColor
            )
            drawPath(
                path = lightColoredPath,
                color = feature.lightColor
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ){
            Text(
                text=feature.title,
                style = MaterialTheme.typography.headlineMedium,
                lineHeight = 26.sp,
                modifier = Modifier
                    .align(Alignment.TopStart)
            )
            Icon(
                painter = painterResource(id = feature.iconId),
                contentDescription = feature.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            Text(
                text = "Start",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        Toast.makeText(contex, ""+feature.title, Toast.LENGTH_SHORT).show()
                }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}
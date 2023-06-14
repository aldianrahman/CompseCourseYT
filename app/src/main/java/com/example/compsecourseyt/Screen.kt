package com.example.compsecourseyt

sealed class Screen(
    val route:String
){
    object MainScreen: Screen("main")
    object SplashScreen: Screen("splash")
    object SecondScreen: Screen("secondScreen")

    fun withArgs(vararg args: String):String{
        return buildString {
            append(route)
            args.forEach {args->
                append("/$args")
            }
        }
    }

}

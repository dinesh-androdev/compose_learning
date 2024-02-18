package com.example.dineshcompose.navigation

sealed class Screen(
    val rout:String
){
    object HomeScreen: Screen("home_screen")
    object DetailsScreen: Screen("home_screen")

    fun withArgs(vararg args:String): String {
        return buildString {
            append(rout)
            args.forEach {
                append("/$it")
            }
        }
    }
}

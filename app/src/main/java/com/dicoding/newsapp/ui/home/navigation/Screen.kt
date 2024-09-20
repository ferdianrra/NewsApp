package com.dicoding.newsapp.ui.home.navigation

sealed class Screen(val route: String) {
    object Home: Screen("screen")
    object DetailNews: Screen("detail/{title}") {
        fun createRoute(title: String) = "detail/${title}"
    }
    object Favorite: Screen("favorite")
}
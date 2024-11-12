package im.juliank.shoppinglist.navigation

sealed class MainNavigation(val route: String) {
    object SplashScreen: MainNavigation("splash")
}
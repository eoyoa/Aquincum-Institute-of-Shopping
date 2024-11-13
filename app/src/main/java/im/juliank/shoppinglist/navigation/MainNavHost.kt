package im.juliank.shoppinglist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import im.juliank.shoppinglist.screen.shoppinglist.ShoppingListScreen
import im.juliank.shoppinglist.screen.splash.SplashScreen

@Composable
fun ShoppingNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainNavigation.SplashScreen.route
    ) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainNavigation.SplashScreen.route){ SplashScreen { navController.navigate(
            MainNavigation.ShoppingListScreen.route) } }
        composable(MainNavigation.ShoppingListScreen.route) { ShoppingListScreen() }
    }
}
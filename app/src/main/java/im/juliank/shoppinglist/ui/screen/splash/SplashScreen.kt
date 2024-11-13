package im.juliank.shoppinglist.ui.screen.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import im.juliank.shoppinglist.R
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onLoadingCompleted: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(3.seconds)
        onLoadingCompleted()
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Rounded.ShoppingCart,
            modifier = modifier.size(50.dp),
            contentDescription = stringResource(R.string.shopping_list_logo)
        )
        Spacer(
            modifier.height(10.dp)
        )
        CircularProgressIndicator(
            modifier = modifier.size(25.dp),
            color = Color.Black
        )
    }
}

@Preview
@Composable
fun OpeningScreenPreview() {
    SplashScreen {}
}
package im.juliank.shoppinglist.screen.shoppinglist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ItemCard(item: Item) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row (
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Rounded.ShoppingCart,
                null
            )
            Column (
                modifier = Modifier.weight(1f)
            ) {
                Text(item.name)
                Text("\$${item.price}")
                Text(item.description)
            }
            Checkbox(
                item.status,
                onCheckedChange = {
                    // todo
                }
            )
        }
    }
}

@Preview
@Composable
fun ItemCardPreview() {
    ItemCard(Item(Category.FOOD, "Test name", "This is a test description", 123.45f, true))
}
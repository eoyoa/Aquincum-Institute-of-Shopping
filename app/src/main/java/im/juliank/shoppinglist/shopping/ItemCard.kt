package im.juliank.shoppinglist.shopping

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.UUID

@Composable
fun ItemCard(item: Item, onEdit: (Item) -> Unit, viewModel: ShoppingListViewModel = viewModel()) {
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
                item.description?.let {
                    Text(it)
                }
            }
            Checkbox(
                item.status,
                onCheckedChange = {
                    viewModel.editItem(
                        Item(
                            item.id,
                            item.category,
                            item.name,
                            item.description,
                            item.price,
                            it
                        )
                    )
                }
            )
            IconButton(
                onClick = {
                    viewModel.deleteItem(item.id)
                }
            ) {
                Icon(
                    Icons.Rounded.Delete,
                    null
                )
            }
            IconButton(
                onClick = {
                    onEdit(item)
                }
            ) {
                Icon(
                    Icons.Rounded.Edit,
                    null
                )
            }
        }
    }
}

@Preview
@Composable
fun ItemCardPreview() {
    ItemCard(Item(UUID.randomUUID(), Category.FOOD, "Test name", null, 123.45f, true), onEdit = {

    })
}
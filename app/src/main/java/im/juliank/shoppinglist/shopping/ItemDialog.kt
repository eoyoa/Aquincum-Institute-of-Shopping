package im.juliank.shoppinglist.shopping

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.UUID

@Composable
fun NewItemDialog(onDismissRequest: () -> Unit, viewModel: ShoppingListViewModel = viewModel()) {
    ItemDialog(
        onSubmit = {
            viewModel.addItem(it)
        },
        buttonText = "Add item",
        onDismissRequest = onDismissRequest
    )
}

@Composable
fun EditItemDialog(item: Item, onDismissRequest: () -> Unit, viewModel: ShoppingListViewModel = viewModel()) {
    ItemDialog(
        itemId = item.id,
        onSubmit = {
            viewModel.editItem(it)
            onDismissRequest()
        },
        buttonText = "Edit item",
        prefilledName = item.name,
        prefilledPrice = item.price.toString(),
        prefilledDescription = item.description,
        prefilledCategory = item.category,
        prefilledStatus = item.status
    )
}

@Composable
fun ItemDialog(
    itemId: UUID = UUID.randomUUID(),
    onSubmit: (Item) -> Unit,
    buttonText: String,
    onDismissRequest: () -> Unit = {},
    prefilledName: String = "",
    prefilledPrice: String = "",
    prefilledDescription: String? = null,
    prefilledCategory: Category = Category.FOOD,
    prefilledStatus: Boolean = false
) {
    var name by remember { mutableStateOf(prefilledName) }
    var price by remember { mutableStateOf(prefilledPrice) }
    var description: String? by remember { mutableStateOf(prefilledDescription) }
    var category by remember { mutableStateOf(prefilledCategory) }
    var bought by remember { mutableStateOf(prefilledStatus) }

    val validName = name != ""
    var validPrice by remember { mutableStateOf(false) }

    val valid = validName && validPrice

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Item name") },
                    isError = !validName
                )
                TextField(
                    value = price,
                    onValueChange = {
                        price = it
                        try {
                            price.toFloat()
                            validPrice = true
                        } catch (_: NumberFormatException) {
                            validPrice = false
                        }
                    },
                    label = { Text("Price") },
                    isError = !validPrice
                )
                TextField(
                    value = description ?: "",
                    onValueChange = { description = if (it != "") it else null },
                    label = { Text("Description (optional)") },
                    // descriptions should be optional
                )
                CategoryDropdown(modifier = Modifier.fillMaxWidth(), preSelected = category) { category = it }
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = bought, onCheckedChange = { bought = it })
                    Text("Bought")
                }
                Button(
                    onClick = {
                        onSubmit(Item(itemId, category, name, description, price.toFloat(), bought))
                        onDismissRequest()
                    },
                    enabled = valid
                ) {
                    Text(buttonText)
                }
            }
        }
    }
}

@Composable
fun CategoryDropdown(
    modifier: Modifier = Modifier,
    preSelected: Category,
    onSelectionChanged: (Category) -> Unit
) {
    var selected by remember { mutableStateOf(preSelected) }
    var expanded by remember { mutableStateOf(false) }

    OutlinedCard(
        modifier = modifier.clickable {
            expanded = !expanded
        }
    ) {
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = preSelected.toString(),
                modifier = Modifier.weight(1f).padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Icon(Icons.Outlined.ArrowDropDown, null, modifier = Modifier.padding(8.dp))
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                Category.entries.forEach { entry ->
                    DropdownMenuItem(
                        text = {
                            Text(entry.toString())
                        },
                        onClick = {
                            selected = entry
                            expanded = false
                            onSelectionChanged(selected)
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun NewItemDialogPreview() {
    NewItemDialog(
        onDismissRequest = {

        }
    )
}
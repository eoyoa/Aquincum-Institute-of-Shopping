package im.juliank.shoppinglist.screen.shoppinglist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(
    modifier: Modifier = Modifier,
    viewModel: ShoppingListViewModel = viewModel()
) {
    var showNewItemDialog by remember { mutableStateOf(false) }

    if (showNewItemDialog) {
        NewItemDialog(onDismissRequest = { showNewItemDialog = false })
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Shopping List")
                },
                actions = {
                    IconButton(onClick = {
                        showNewItemDialog = true
                    }) {
                        Icon(
                            Icons.Rounded.Add,
                            null
                        )
                    }
                },
            )
        }
    ) { padding ->
        Column(
            modifier = modifier.padding(padding)
        ) {
            val allItems = viewModel.getAllItems()
            if (allItems.isEmpty()) Text("No items")
            else {
                LazyColumn(
                    modifier = modifier.fillMaxHeight()
                ) {
                    items(allItems) {
                        ItemCard(it)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ShoppingListScreenPreview() {
    ShoppingListScreen()
}
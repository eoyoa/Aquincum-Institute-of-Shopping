package im.juliank.shoppinglist.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import im.juliank.shoppinglist.R
import im.juliank.shoppinglist.data.Category
import im.juliank.shoppinglist.data.ItemEntity
import im.juliank.shoppinglist.shopping.EditItemDialog
import im.juliank.shoppinglist.shopping.ItemCard
import im.juliank.shoppinglist.shopping.NewItemDialog
import im.juliank.shoppinglist.shopping.ShoppingListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(
    modifier: Modifier = Modifier,
    viewModel: ShoppingListViewModel = hiltViewModel()
) {
    var showNewItemDialog by rememberSaveable { mutableStateOf(false) }
    var currentItemToEdit: ItemEntity? by rememberSaveable { mutableStateOf(null) }
    var filteredCategory: Category? by rememberSaveable { mutableStateOf(null) }

    val allItems by viewModel.getAllItems(filteredCategory).collectAsState(emptyList())

    if (showNewItemDialog) {
        NewItemDialog(onDismissRequest = { showNewItemDialog = false })
    }
    else if (currentItemToEdit != null) {
        currentItemToEdit?.let {
            EditItemDialog(it, onDismissRequest = { currentItemToEdit = null })
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.shopping_screen_title))
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
                    IconButton(
                        onClick = {
                            viewModel.deleteAllItems()
                        }
                    ) {
                        Icon(
                            Icons.Rounded.Delete,
                            null
                        )
                    }
                    FilterButton(
                        filteredCategory,
                        onSelectionChanged = {
                            filteredCategory = it
                        }
                    )
                },
            )
        }
    ) { padding ->
        Column(
            modifier = modifier.padding(padding)
        ) {
            if (allItems.isEmpty()) Text(
                stringResource(
                    R.string.no_items,
                    if (filteredCategory != null) stringResource(R.string.no_items_filtered) else ""
                ))
            else {
                LazyColumn(
                    modifier = modifier.fillMaxHeight()
                ) {
                    items(allItems) {
                        ItemCard(
                            it,
                            onEdit = {
                                currentItemToEdit = it
                            }
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun FilterButton(
    preSelected: Category?,
    onSelectionChanged: (Category?) -> Unit
) {
    var selected by remember { mutableStateOf(preSelected) }
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = {
            expanded = !expanded
        }
    ) {
        Icon(Icons.AutoMirrored.Rounded.List, null, modifier = Modifier.padding(8.dp))
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text("")
                },
                onClick = {
                    selected = null
                    expanded = false
                    onSelectionChanged(null)
                }
            )
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

@Preview
@Composable
fun ShoppingListScreenPreview() {
    ShoppingListScreen()
}
package im.juliank.shoppinglist.shopping

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import java.util.UUID

class ShoppingListViewModel: ViewModel() {
    var items = mutableStateListOf<Item>()

    fun addItem(category: Category, name: String, description: String?, price: Float, status: Boolean) {
        val item = Item(UUID.randomUUID(), category, name, description, price, status)
        Log.d("ADD_ITEM", "adding new item $item")
        items.add(item)
        Log.d("ADD_ITEM", "done, items are now $items")
    }

    fun deleteItem(uuid: UUID) {
        items.removeIf {
            it.id == uuid
        }
    }

    fun deleteAllItems() {
        items.clear()
    }
}

data class Item(val id: UUID, val category: Category, val name: String, val description: String?, val price: Float, val status: Boolean)

enum class Category {
    FOOD,
    SUPPLY,
    BOOK
}
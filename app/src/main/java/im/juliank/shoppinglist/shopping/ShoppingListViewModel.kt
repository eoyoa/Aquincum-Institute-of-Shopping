package im.juliank.shoppinglist.shopping

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import java.util.UUID

class ShoppingListViewModel: ViewModel() {
    var items = mutableStateListOf<Item>()

    fun addItem(item: Item) {
        Log.d("ADD_ITEM", "adding new item $item")
        items.add(item)
        Log.d("ADD_ITEM", "done, items are now $items")
    }

    fun deleteItem(id: UUID) {
        items.removeIf {
            it.id == id
        }
    }

    fun deleteAllItems() {
        items.clear()
    }

    fun editItem(item: Item) {
        var toEdit = items.find { it.id == item.id }
        toEdit?.let {
            val index = items.indexOf(toEdit)
            items.removeAt(index)
            items.add(index, item)
        }
    }
}

data class Item(var id: UUID, var category: Category, var name: String, var description: String?, var price: Float, var status: Boolean)

enum class Category {
    FOOD,
    SUPPLY,
    BOOK
}
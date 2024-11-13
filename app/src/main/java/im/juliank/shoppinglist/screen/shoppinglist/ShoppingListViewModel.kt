package im.juliank.shoppinglist.screen.shoppinglist

import android.util.Log
import androidx.lifecycle.ViewModel

class ShoppingListViewModel: ViewModel() {
    private val items = ArrayList<Item>()

    fun addItem(item: Item) {
        Log.d("ADD_ITEM", "adding new item $item")
        items.add(item)
        Log.d("ADD_ITEM", "done, items are now $items")
    }

    fun getAllItems(): List<Item> {
        return items
    }
}

data class Item(val category: Category, val name: String, val description: String?, val price: Float, val status: Boolean)

enum class Category {
    FOOD,
    SUPPLY,
    BOOK
}
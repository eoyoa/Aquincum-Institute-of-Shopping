package im.juliank.shoppinglist.shopping

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import im.juliank.shoppinglist.data.ItemDAO
import im.juliank.shoppinglist.data.ItemEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    val itemDAO: ItemDAO
) : ViewModel() {
    fun getAllItems(): Flow<List<ItemEntity>> {
        return itemDAO.getAllItems()
    }

    fun addItem(item: ItemEntity) {
        Log.d("ADD_ITEM", "adding new item $item")
        viewModelScope.launch(Dispatchers.IO) {
            itemDAO.insert(item)
        }
        Log.d("ADD_ITEM", "done")
    }

    fun deleteItem(item: ItemEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            itemDAO.delete(item)
        }
    }

    fun deleteAllItems() {
        viewModelScope.launch(Dispatchers.IO) {
            itemDAO.deleteAllItems()
        }
    }

    fun editItem(item: ItemEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            itemDAO.update(item)
        }
    }
}


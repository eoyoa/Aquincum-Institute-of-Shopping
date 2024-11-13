package im.juliank.shoppinglist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import im.juliank.shoppinglist.R
import java.io.Serializable
import java.util.UUID

@Entity(tableName = "items")
data class ItemEntity (
    @PrimaryKey(autoGenerate = false) var id: UUID,
    @ColumnInfo(name = "category") var category: Category,
    @ColumnInfo(name = "itemname") var name: String,
    @ColumnInfo(name = "description") var description: String?,
    @ColumnInfo(name = "price") var price: Float,
    @ColumnInfo(name = "status") var status: Boolean
): Serializable

enum class Category(val drawableId: Int) {
    FOOD(R.drawable.baseline_fastfood_24),
    SUPPLY(R.drawable.baseline_newspaper_24),
    BOOK(R.drawable.baseline_menu_book_24)
}
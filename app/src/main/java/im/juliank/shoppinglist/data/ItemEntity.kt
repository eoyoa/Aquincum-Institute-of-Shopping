package im.juliank.shoppinglist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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

enum class Category {
    FOOD,
    SUPPLY,
    BOOK
}
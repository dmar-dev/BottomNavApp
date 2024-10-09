package pl.dmardev.bottomnavapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val date: Long,
    val price: Float,
    val description: String,
    val type: String,
    val category: String
)

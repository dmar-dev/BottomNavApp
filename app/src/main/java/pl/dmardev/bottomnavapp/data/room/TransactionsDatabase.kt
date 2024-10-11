package pl.dmardev.bottomnavapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.dmardev.bottomnavapp.data.models.Transaction

@Database(entities = [Transaction::class], version = 1, exportSchema = false)
abstract class TransactionsDatabase : RoomDatabase() {

    abstract fun transactionsDao(): TransactionsDao
}

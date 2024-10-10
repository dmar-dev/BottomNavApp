package pl.dmardev.bottomnavapp.data.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import pl.dmardev.bottomnavapp.data.models.CategoryTotal
import pl.dmardev.bottomnavapp.data.models.Transaction

@Dao
interface TransactionsDao {

    @Insert
    fun insertTransactions(transaction : Transaction)

    @Update
    fun updateTransactions(transaction: Transaction)

    @Delete
    fun deleteTransactions(transactions: List<Transaction>)

    @Query("SELECT * FROM transactions_table ORDER BY date DESC")
    fun getAllTransactions(): Flow<List<Transaction>>

    @Query("SELECT * FROM transactions_table WHERE type = 'INCOME' ")
    fun getAllIncomes(): Flow<List<Transaction>>

    @Query("SELECT * FROM transactions_table WHERE type = 'OUTCOME' ")
    fun getAllOutcomes(): Flow<List<Transaction>>

    @Query("SELECT category, SUM(price) as total FROM transactions_table WHERE type = 'INCOME' GROUP BY category")
    fun getSumOfIncomesGroupByCategory(): Flow<List<CategoryTotal>>

    @Query("SELECT category, SUM(price) as total FROM transactions_table WHERE type = 'OUTCOME' GROUP BY category")
    fun getSumOfOutcomesGroupByCategory(): Flow<List<CategoryTotal>>
}

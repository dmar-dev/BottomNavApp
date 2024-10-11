package pl.dmardev.bottomnavapp.data

import android.app.Application
import pl.dmardev.bottomnavapp.data.models.Transaction
import pl.dmardev.bottomnavapp.data.room.DatabaseInstance

// context: Context is possible (what You want)
class TransactionsRepository(app: Application) {

    private val transactionsDao = DatabaseInstance.getInstance(app).transactionsDao()

    suspend fun insertTransaction(transaction: Transaction) {
        transactionsDao.insertTransaction(transaction)
    }

    suspend fun updateTransaction(transaction: Transaction) {
        transactionsDao.updateTransaction(transaction)
    }

    suspend fun deleteTransactions(transactions: List<Transaction>) {
        transactionsDao.deleteTransactions(transactions)
    }

    fun getAllTransactions() = transactionsDao.getAllTransactions()
    fun getAllIncomes() = transactionsDao.getAllIncomes()
    fun getAllOutcomes() = transactionsDao.getAllOutcomes()
    fun getSumOfIncomeByCategory() = transactionsDao.getSumOfIncomesGroupByCategory()
    fun getSumOfOutcomeByCategory() = transactionsDao.getSumOfOutcomesGroupByCategory()
}

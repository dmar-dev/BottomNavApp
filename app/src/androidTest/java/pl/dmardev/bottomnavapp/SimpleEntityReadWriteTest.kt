package pl.dmardev.bottomnavapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import pl.dmardev.bottomnavapp.data.room.TransactionsDao
import pl.dmardev.bottomnavapp.data.room.TransactionsDatabase
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SimpleEntityReadWriteTest {

    private lateinit var transactionsDao: TransactionsDao
    private lateinit var db: TransactionsDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, TransactionsDatabase::class.java)
            .build()

        transactionsDao = db.transactionsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeTransactionAndReadInList() {

//        assertThat()
    }
}
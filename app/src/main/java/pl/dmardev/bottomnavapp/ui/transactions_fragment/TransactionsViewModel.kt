package pl.dmardev.bottomnavapp.ui.transactions_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TransactionsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Transactions App"
    }
    val text: LiveData<String> = _text
}
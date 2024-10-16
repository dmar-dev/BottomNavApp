package pl.dmardev.bottomnavapp.ui.edit_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditTransactionViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Edit Fragment"
    }
    val text: LiveData<String> = _text
}

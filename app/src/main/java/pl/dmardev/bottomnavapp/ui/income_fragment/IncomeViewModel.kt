package pl.dmardev.bottomnavapp.ui.income_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IncomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Income Fragment"
    }
    val text: LiveData<String> = _text
}

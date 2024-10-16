package pl.dmardev.bottomnavapp.ui.outcome_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OutcomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Outcome Fragment"
    }
    val text: LiveData<String> = _text
}
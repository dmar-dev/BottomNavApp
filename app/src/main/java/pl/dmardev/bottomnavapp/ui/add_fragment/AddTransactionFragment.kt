package pl.dmardev.bottomnavapp.ui.add_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import pl.dmardev.bottomnavapp.MainViewModel
import pl.dmardev.bottomnavapp.data.models.Transaction
import pl.dmardev.bottomnavapp.data.models.TransactionCategory
import pl.dmardev.bottomnavapp.data.models.TransactionType
import pl.dmardev.bottomnavapp.databinding.FragmentAddTransactionBinding
import pl.dmardev.bottomnavapp.ui.date_picker.TransactionDatePicker
import java.util.*

class AddTransactionFragment : Fragment() {

    private val viewModel by viewModels<AddTransactionViewModel>()
    private val mainVm by activityViewModels<MainViewModel>()
    private var _binding: FragmentAddTransactionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTransactionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // adapter for spinner
        val adapter = ArrayAdapter(
            requireContext(),
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            TransactionCategory.values().map { enum -> enum.name }
        )
        binding.categorySpinner.adapter = adapter

        binding.calendarImg.setOnClickListener {
            showDatePickerDialog()
        }

        binding.saveBtn.setOnClickListener {
            val trans = createTransaction()
            mainVm.insertTransaction(trans)
            requireActivity().onBackPressed()
        }
    }

    private fun showDatePickerDialog() {
        val newDatePicker = TransactionDatePicker { day,month,year ->
            binding.dayTv.text = day.toString()
            binding.monthTv.text = month.toString()
            binding.yearTv.text = year.toString()

            val date = Calendar.getInstance()
            date.set(day, month, year)
            viewModel.date = date.timeInMillis
        }

        newDatePicker.show(parentFragmentManager, "DatePicker")
    }

    private fun createTransaction(): Transaction {
        val type = when (binding.radioGroup.checkedRadioButtonId) {
            binding.incomeRb.id -> TransactionType.INCOME
            else -> TransactionType.OUTCOME
        }

        val category = when (binding.categorySpinner.selectedItem.toString()) {
            "FOOD" -> TransactionCategory.FOOD
            "OTHERS" -> TransactionCategory.OTHERS
            "HOUSEHOLD" -> TransactionCategory.HOUSEHOLD
            "TRANSPORTATION" -> TransactionCategory.TRANSPORTATION
            else -> TransactionCategory.OTHERS
        }

        val amount = binding.amountEt.text.toString()
        val desc = binding.descEt.text.toString()

        return Transaction(0, viewModel.date, amount.toFloat(), desc, type, category)
    }

    /*override fun onDetach() {
        super.onDetach()
        _binding = null
    }*/
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

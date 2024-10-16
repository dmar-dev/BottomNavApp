package pl.dmardev.bottomnavapp.ui.edit_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import pl.dmardev.bottomnavapp.databinding.FragmentAddTransactionBinding

class EditTransactionFragment : Fragment() {

    private var _binding: FragmentAddTransactionBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<EditTransactionViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        /*val dashboardViewModel =
                ViewModelProvider(this).get(IncomeViewModel::class.java)*/

        _binding = FragmentAddTransactionBinding.inflate(inflater, container, false)
        return binding.root
//        val root: View = binding.root

        /*val textView: TextView = EditTransactionViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
//        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

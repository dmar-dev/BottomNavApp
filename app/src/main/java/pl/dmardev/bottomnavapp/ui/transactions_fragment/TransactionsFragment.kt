/*
 * Author Rafał Rejek strefakursow.pl
 */
package pl.dmardev.bottomnavapp.ui.transactions_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import pl.dmardev.bottomnavapp.MainViewModel
import pl.dmardev.bottomnavapp.databinding.FragmentTransactionsBinding
import pl.dmardev.bottomnavapp.ui.adapters.TransactionsAdapter

class TransactionsFragment : Fragment() {

    private val viewModel by viewModels<TransactionsViewModel>()
    private val mainVm by activityViewModels<MainViewModel>()
    private var _binding: FragmentTransactionsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransactionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        mainVm.getAllTransactions().observe(viewLifecycleOwner) { transactions ->
            binding.recyclerView.adapter = TransactionsAdapter(
                transactions,
                { transaction, position ->
                Log.d("TEST", "Jest trans: ${transaction.toString()}")
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

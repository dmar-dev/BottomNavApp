package pl.dmardev.bottomnavapp.ui.outcome_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import pl.dmardev.bottomnavapp.databinding.FragmentOutcomeBinding

class OutcomeFragment : Fragment() {

    private var _binding: FragmentOutcomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val outcomeViewModel =
                ViewModelProvider(this).get(OutcomeViewModel::class.java)

        _binding = FragmentOutcomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textOutcome
        outcomeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
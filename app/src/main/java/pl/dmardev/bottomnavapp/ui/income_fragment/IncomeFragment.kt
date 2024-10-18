/*
 * Author Rafał Rejek strefakursow.pl
 */
package pl.dmardev.bottomnavapp.ui.income_fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import pl.dmardev.bottomnavapp.MainViewModel
import pl.dmardev.bottomnavapp.databinding.FragmentIncomeBinding

class IncomeFragment : Fragment() {

    private val viewModel by viewModels<IncomeViewModel>()
    private val mainVm by activityViewModels<MainViewModel>()

    private var _binding: FragmentIncomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIncomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // look of PieChart
        binding.incomePieChart.apply {
            isDrawHoleEnabled = true
            setUsePercentValues(true)
            setEntryLabelTextSize(18f)
            setEntryLabelColor(Color.WHITE)
            centerText = "Incomes"
            setCenterTextSize(24f)
            description.isEnabled = false
            setTransparentCircleAlpha(50)

            // listener that listens for a specific slice to be selected
            setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    binding.incomePieChart.centerText = e?.y.toString() + "\nPLN"
                    binding.incomePieChart.invalidate()
                }

                override fun onNothingSelected() {
                    binding.incomePieChart.centerText = "Incomes"
                    binding.incomePieChart.invalidate()
                }
            })
        }

        // prepare the chart
        mainVm.getSumOfIncomesByCategory().observe(viewLifecycleOwner) { transactions ->

            // three abstract layers
            // 1. we need to add the data
            val entries = ArrayList<PieEntry>()
            for (tran in transactions) {
                val pieEntry = PieEntry(tran.total, tran.category.name.lowercase())
                entries.add(pieEntry)
            }

            // 2. look of chart
            val pieDataSet = PieDataSet(entries, "")
            // four colors because four values in data/models/TransactionCategory
            val colors= listOf(
                Color.parseColor("#9038FF"),
                Color.parseColor("#45197D"),
                Color.parseColor("#E536AB"),
                Color.parseColor("#5C03BC")
            )
            pieDataSet.colors = colors

            // 3. settings of chart
            val pieData = PieData(pieDataSet)
            pieData.setDrawValues(true)
            pieData.setValueFormatter(PercentFormatter(binding.incomePieChart))
            pieData.setValueTextSize(12f)
            pieData.setValueTextColor(Color.WHITE)

            binding.incomePieChart.apply {
                legend.isEnabled = false
                data = pieData // load new data
                animateY(500, Easing.EaseInOutQuad)
                invalidate() //redraw
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

/*
 * Author Rafał Rejek strefakursow.pl
 */
package pl.dmardev.bottomnavapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.dmardev.bottomnavapp.R
import pl.dmardev.bottomnavapp.data.models.Transaction
import pl.dmardev.bottomnavapp.data.models.TransactionType
import pl.dmardev.bottomnavapp.databinding.TransactionRowBinding
import java.text.SimpleDateFormat
import java.util.*

class TransactionsAdapter(
    private val transactions: List<Transaction>,
    private val onClick: (Transaction, Int) -> Unit
) : RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder>() {

    inner class TransactionViewHolder(binding: TransactionRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClick(transactions[adapterPosition], adapterPosition)
            }
        }

        val date = binding.dateTv
        val price = binding.priceTv
        val category = binding.categoryTv
        val type = binding.typeTv
        val icon = binding.imageTv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(
            TransactionRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        bindData(holder, position)
    }

    fun bindData(holder: TransactionViewHolder, position: Int) {
        val sdf = SimpleDateFormat("dd-MM-yyyy")
        val date = Date(transactions[position].date)
        val datePlaceholder = sdf.format(date)

        val typeIconResource = when (transactions[position].type) {
            TransactionType.INCOME -> R.drawable.ic_wallet_add
            TransactionType.OUTCOME -> R.drawable.ic_wallet_remove
        }

        holder.price.text = transactions[position].price.toString()
        holder.category.text = transactions[position].category.name
        holder.type.text = transactions[position].type.name
        holder.date.text = datePlaceholder
        holder.icon.setImageResource(typeIconResource)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }
}

package com.example.shoppinglistapp.other

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglistapp.R
import com.example.shoppinglistapp.data.db.entities.ShoppingItem
import com.example.shoppinglistapp.ui.shoppinglist.ShoppingViewModel
import com.example.shoppinglistapp.databinding.ShoppingItemBinding

class ShoppingItemAdaptor(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdaptor.ShoppingViewHolder>() {

    inner class ShoppingViewHolder(private val binding: ShoppingItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(shoppingItem: ShoppingItem) {
            binding.tvName.text = shoppingItem.name
            binding.tvAmount.text = shoppingItem.amount.toString()

            binding.ivDelete.setOnClickListener {
                viewModel.delete(shoppingItem)
            }

            binding.ivPlus.setOnClickListener {
                shoppingItem.amount++
                viewModel.upsert(shoppingItem)
                binding.tvAmount.text = shoppingItem.amount.toString()  // Update displayed amount
            }

            binding.ivMinus.setOnClickListener {
                if (shoppingItem.amount > 0) {
                    shoppingItem.amount--
                    viewModel.upsert(shoppingItem)
                    binding.tvAmount.text = shoppingItem.amount.toString()  // Update displayed amount
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val binding = ShoppingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentShoppingItem = items[position]
        holder.bind(currentShoppingItem)
    }
}

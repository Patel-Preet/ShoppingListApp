package com.example.shoppinglistapp.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.example.shoppinglistapp.data.db.entities.ShoppingItem
import com.example.shoppinglistapp.data.repositories.ShoppingRepositories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//As this class takes in a parameter we need a ViewModelFactory class
class ShoppingViewModel(private val repositories: ShoppingRepositories): ViewModel() {

    fun upsert(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch{
        repositories.upsert(item)
    }

    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repositories.delete(item)
    }

    fun getShoppingItems() = repositories.getShoppingItems()
}
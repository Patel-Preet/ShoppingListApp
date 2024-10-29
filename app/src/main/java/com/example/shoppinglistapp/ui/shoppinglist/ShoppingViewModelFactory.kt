package com.example.shoppinglistapp.ui.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglistapp.data.repositories.ShoppingRepositories

class ShoppingViewModelFactory(private val repositories: ShoppingRepositories): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShoppingViewModel(repositories) as T
    }
}
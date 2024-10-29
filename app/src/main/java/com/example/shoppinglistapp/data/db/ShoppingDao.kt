package com.example.shoppinglistapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shoppinglistapp.data.db.entities.ShoppingItem

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(shoppingItem: ShoppingItem)

    @Delete
    suspend fun delete(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getShoppingItems() : LiveData<List<ShoppingItem>>
}
package com.example.shoppinglistapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppinglistapp.data.db.entities.ShoppingItem

@Database(entities = [ShoppingItem::class], version = 1)
abstract class ShoppingDatabase: RoomDatabase() {

    abstract fun getShoppingDao() : ShoppingDao

    //equal to java static -> usage ShoppingDatabase().
    companion object{

        //Volatile: reads and writes to this field are atomic and writes are always made visible to other threads
        @Volatile
        private var instance: ShoppingDatabase? = null
        private var LOCK = Any()

        //this invoke is called every time user calls ShoppingDatabase().
        //synchronized because no 2 threads should be able to create a database
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, ShoppingDatabase::class.java, "ShoppingDB").build()
    }
}
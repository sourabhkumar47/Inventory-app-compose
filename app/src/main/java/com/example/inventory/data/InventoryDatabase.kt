package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {
    //So the database know about the DAO
    abstract fun itemDao(): ItemDao

    //allows access to the methods to create or
    // get the database and uses the class name as the qualifier
    companion object{
        @Volatile
        private var Instance:InventoryDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): InventoryDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }

    }
}
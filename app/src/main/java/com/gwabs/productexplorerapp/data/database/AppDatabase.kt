package com.gwabs.productexplorerapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gwabs.productexplorerapp.data.model.Product

@Database(entities = [Product::class], version = 2, exportSchema = true) // Increment version number
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
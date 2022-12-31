package com.hahn.orgs.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hahn.orgs.database.dao.ProductDao
import com.hahn.orgs.model.Product

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
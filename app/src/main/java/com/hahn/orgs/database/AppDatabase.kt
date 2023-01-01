package com.hahn.orgs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hahn.orgs.database.converter.Converters
import com.hahn.orgs.database.dao.ProductDao
import com.hahn.orgs.model.Product

@Database(entities = [Product::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    
    companion object {
        fun getInstance(context: Context) {
            Room.databaseBuilder(
                context,
                AppDatabase::class.java, "orgs.db"
            ).build()
        }
    }
}
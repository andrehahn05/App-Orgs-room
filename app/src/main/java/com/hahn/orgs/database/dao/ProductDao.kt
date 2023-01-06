package com.hahn.orgs.database.dao

import androidx.room.*
import com.hahn.orgs.model.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM Product")
    fun findAll(): List<Product>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun store( product:Product)
    
    @Delete
    fun remove( product: Product)
    
    @Query("SELECT * FROM Product WHERE id = :id")
    fun findById(id: Long) : Product?
    
}
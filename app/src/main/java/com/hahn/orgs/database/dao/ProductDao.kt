package com.hahn.orgs.database.dao

import androidx.room.*
import com.hahn.orgs.model.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM Product")
    fun findAll(): List<Product>
    
    @Insert
    fun store(vararg product:Product)
    
    @Update
    fun update(product: Product)
    
    @Delete
    fun remove( product: Product)
    
}
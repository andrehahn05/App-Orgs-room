package com.hahn.orgs.database.dao

import androidx.room.*
import com.hahn.orgs.model.Product
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDao {
    @Query("SELECT * FROM Product")
    fun findAll(): Flow<List<Product>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun store(vararg product:Product)
    
    @Delete
    suspend fun remove(vararg product: Product)
    
    @Query("SELECT * FROM Product WHERE id = :id")
    fun findById(id: Long) : Flow<Product?>
    
}
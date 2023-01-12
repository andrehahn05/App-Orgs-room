package com.hahn.orgs.database.dao

import androidx.room.*
import com.hahn.orgs.model.Product
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDao {
    @Query("SELECT * FROM Product")
    fun findAll(): Flow<List<Product>>
    
    @Query("SELECT * FROM product WHERE userId = :userId")
    fun find(userId: String): Flow<List<Product>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(vararg product:Product)
    
    @Delete
    suspend fun remove(vararg product: Product)
    
    @Query("SELECT * FROM Product WHERE id = :id")
    fun findById(id: Long) : Flow<Product?>
    
    @Query("SELECT * FROM Product ORDER BY name ASC")
    fun orderNameAsc() : Flow<List<Product>>
    
    @Query("SELECT * FROM Product ORDER BY name DESC")
    fun orderNameDesc() : Flow<List<Product>>
    
    @Query("SELECT * FROM Product ORDER BY price ASC")
    fun orderValueAsc() : Flow<List<Product>>
    
    @Query("SELECT * FROM Product ORDER BY price DESC")
    fun orderValueDesc() : Flow<List<Product>>
    
    
    
}
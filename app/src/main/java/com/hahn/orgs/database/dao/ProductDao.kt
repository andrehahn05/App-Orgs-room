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
    
    @Query("SELECT * FROM Product WHERE userId = :userId ORDER BY name ASC")
    fun orderNameAsc(userId: String) : Flow<List<Product>>
    
    @Query("SELECT * FROM Product WHERE userId = :userId ORDER BY name DESC")
    fun orderNameDesc(userId: String) : Flow<List<Product>>
    
    @Query("SELECT * FROM Product WHERE userId = :userId ORDER BY price ASC")
    fun orderValueAsc(userId: String) : Flow<List<Product>>
    
    @Query("SELECT * FROM Product WHERE userId = :userId ORDER BY price DESC")
    fun orderValueDesc(userId: String) : Flow<List<Product>>
    
    
    
}
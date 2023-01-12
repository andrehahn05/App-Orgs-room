package com.hahn.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hahn.orgs.model.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {
    @Insert
    suspend fun add(user: User)
    
    @Query("SELECT * FROM User WHERE id = :userId AND password = :password")
    suspend fun auth(userId: String, password:String): User?
    
    @Query("SELECT * FROM User WHERE id = :userId")
    fun findById(userId: String) : Flow<User>
}
package com.hahn.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.hahn.orgs.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun store(user: User)
}
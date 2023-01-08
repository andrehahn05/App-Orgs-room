package com.hahn.orgs.database.dao

import androidx.room.Insert
import com.hahn.orgs.model.User

interface UserDao {
    @Insert
    suspend fun store(user: User)
}
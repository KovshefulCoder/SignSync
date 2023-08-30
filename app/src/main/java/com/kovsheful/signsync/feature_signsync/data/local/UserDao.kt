package com.kovsheful.signsync.feature_signsync.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.kovsheful.signsync.feature_signsync.domain.models.User

@Dao
interface UserDao {
    @Upsert
    suspend fun upsert(user: User)

    @Query("SELECT * FROM user")
    suspend fun getUser(): User
}
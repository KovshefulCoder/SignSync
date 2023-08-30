package com.kovsheful.signsync.feature_signsync.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.kovsheful.signsync.feature_signsync.domain.models.User

@Dao
interface UserDao {
    @Upsert
    suspend fun upsert(user: User)

    @Query("SELECT * FROM user")
    suspend fun getUser(): User

    @Query("SELECT password FROM user WHERE id=:id")
    suspend fun getUserPasswordByID(id: Int): String

    @Query("DELETE FROM user")
    suspend fun clearUser()
}
package com.kovsheful.signsync.feature_signsync.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kovsheful.signsync.feature_signsync.domain.models.User

@Database(entities = [User::class], version = 1)
abstract class SignAndSyncDatabase: RoomDatabase() {
    abstract fun UserDao(): UserDao
}
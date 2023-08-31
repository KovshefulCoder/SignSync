package com.kovsheful.signsync.feature_signsync.data.repository

import com.kovsheful.signsync.feature_signsync.data.local.UserDao
import com.kovsheful.signsync.feature_signsync.domain.models.User
import com.kovsheful.signsync.feature_signsync.domain.repository.SignAndSyncRepository

class SignAndSyncRepositoryImpl(
    private val userDao: UserDao
) : SignAndSyncRepository {
    override suspend fun getUser(): User {
        return userDao.getUser()
    }

    override suspend fun upsert(user: User) {
        userDao.upsert(user)
    }

    override suspend fun getUserPasswordByID(id: Int): String {
        return userDao.getUserPasswordByID(id)
    }

    override suspend fun clearUser() {
        userDao.clearUser()
    }

}
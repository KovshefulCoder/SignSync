package com.kovsheful.signsync.feature_signsync.domain.repository

import com.kovsheful.signsync.feature_signsync.domain.models.User

interface SignAndSyncRepository {
    suspend fun getUser(): User
    suspend fun upsert(user: User)
}
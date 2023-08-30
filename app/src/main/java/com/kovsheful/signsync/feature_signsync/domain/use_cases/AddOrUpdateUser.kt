package com.kovsheful.signsync.feature_signsync.domain.use_cases

import com.kovsheful.signsync.feature_signsync.domain.models.User
import com.kovsheful.signsync.feature_signsync.domain.repository.SignAndSyncRepository

class AddOrUpdateUser(
    private val repository: SignAndSyncRepository
) {
    suspend operator fun invoke (user: User) {
        return repository.upsert(user)
    }
}
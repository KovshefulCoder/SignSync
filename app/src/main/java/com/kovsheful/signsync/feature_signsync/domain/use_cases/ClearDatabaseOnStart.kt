package com.kovsheful.signsync.feature_signsync.domain.use_cases

import com.kovsheful.signsync.feature_signsync.domain.repository.SignAndSyncRepository

class ClearUserOnStart(
    private val repository: SignAndSyncRepository
) {
    suspend operator fun invoke () {
        return repository.clearUser()
    }
}
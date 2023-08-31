package com.kovsheful.signsync.feature_signsync.domain.use_cases

import com.kovsheful.signsync.feature_signsync.domain.repository.SignAndSyncRepository

class GetUserPasswordByID (
    private val repository: SignAndSyncRepository
) {
    suspend operator fun invoke(id: Int): String {
        return repository.getUserPasswordByID(id)
    }
}
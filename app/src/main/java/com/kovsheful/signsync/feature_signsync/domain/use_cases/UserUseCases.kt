package com.kovsheful.signsync.feature_signsync.domain.use_cases

data class UserUseCases(
    val getUser: GetUser,
    val clearUserOnStart: ClearUserOnStart,
    val addOrUpdateUser: AddOrUpdateUser,
    val getUserPasswordByID: GetUserPasswordByID,
)
